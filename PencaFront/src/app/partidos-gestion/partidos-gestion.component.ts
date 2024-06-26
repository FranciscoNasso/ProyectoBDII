import { Component, OnInit } from '@angular/core';
import { PartidoService } from '../Services/partido/partido.service';
import { PaisService } from '../Services/pais/pais.service';
import { ToastrService } from 'ngx-toastr';
import * as countries from 'i18n-iso-countries';

@Component({
  selector: 'app-partidos-gestion',
  templateUrl: './partidos-gestion.component.html',
  styleUrls: ['./partidos-gestion.component.css']
})
export class PartidosGestionComponent implements OnInit {
  paises: any[] = [];
  partidos: any[] = [];
  nuevoPartido: any = {
    nombre: '',
    fecha: '',
    hora: '',
    id_pais_local: '',
    id_pais_visitante: '',
    goles_pais_local: null,
    goles_pais_visitante: null
  };
  mostrarFormulario: boolean = true;

  constructor(private partidoService: PartidoService, private paisService: PaisService, private toastr: ToastrService) {
    countries.registerLocale(require('i18n-iso-countries/langs/es.json'));
  }
  
  ngOnInit() {
    this.loadPartidos();
    this.loadPaises();
  }
  
  loadPartidos() {
    this.partidoService.getPartidos().subscribe((data: any[]) => {
      this.partidos = data;
    });
  }

  puedeModificar(fecha: string, hora: string): boolean {
    return new Date(`${fecha}T${hora}`) >= new Date();
  }

  guardarGoles(partido: any) {
    this.partidoService.addGoles(partido).subscribe(
      (response: any) => {
        console.log('Partido actualizado', response);
        this.toastr.success('Goles guardados exitosamente');
      },
      (error: any) => {
        console.error('Error al actualizar goles', error);
        this.toastr.error('Error al guardar goles');
      }
    );
  }

  eliminarPartido(id: number) {
    this.partidoService.deletePartido(id).subscribe(
      (response: any) => {
        console.log('Partido eliminado', response);
        this.loadPartidos(); 
        this.toastr.success('Partido eliminado exitosamente');
      },
      (error: any) => {
        console.error('Error al eliminar partido', error);
        this.toastr.error('Error al eliminar partido');
      }
    );
  }

  agregarPartido() {
    this.partidoService.createPartido(this.nuevoPartido).subscribe(
      (response: any) => {
        console.log('Partido agregado', response);
        this.loadPartidos(); 
        this.nuevoPartido = {
          nombre: '',
          fecha: '',
          hora: '',
          id_pais_local: '',
          id_pais_visitante: '',
          goles_pais_local: null,
          goles_pais_visitante: null
        };
        this.toastr.success('Partido agregado exitosamente');
      },
      (error: any) => {
        console.error('Error al agregar partido', error);
        this.toastr.error('Error al agregar partido');
      }
    );
  }

  getFlagUrl(pais: string): string {
    const countryCode = countries.getAlpha2Code(pais, 'es');
    if (!countryCode) {
      return '';
    }
    return `https://hatscripts.github.io/circle-flags/flags/${countryCode.toLowerCase()}.svg`;
  }

  transformarFecha(fecha: string): string {
    const [year, month, day] = fecha.split('-');
    return `${month}/${day}`;
  }

  toggleForm() {
    this.mostrarFormulario = !this.mostrarFormulario;
  }

  loadPaises() {
    this.paisService.getPaises().subscribe((data: any[]) => {
      this.paises = data;
    });
  }
}
