import { Component, OnInit } from '@angular/core';
import { PartidoService } from '../Services/partido/partido.service';
import * as countries from 'i18n-iso-countries';

@Component({
  selector: 'app-partidos-gestion',
  templateUrl: './partidos-gestion.component.html',
  styleUrls: ['./partidos-gestion.component.css']
})
export class PartidosGestionComponent implements OnInit {
  partidos: any[] = [];
  errores: any[] = [];
  nuevoPartido: any = {
    nombre: '',
    fecha: '',
    hora: '',
    paisLocal: '',
    paisVisitante: '',
    goles_pais_local: null,
    goles_pais_visitante: null
  };

  constructor(private partidoService: PartidoService) { }
  
  ngOnInit() {
    this.loadPartidos();
  }
  
  loadPartidos() {
    this.partidoService.getPartidos().subscribe((data: any[]) => {
      this.partidos = data;
    });
  }

  puedeModificar(fecha: string, hora: string): boolean {
    return new Date(`${fecha}T${hora}`) <= new Date();
  }

  guardarGoles(partido: any) {
    // Lógica para guardar los goles del partido
    this.partidoService.addGoles(partido).subscribe(response => {
      console.log('Partido actualizado', response);
    });
  }

  eliminarPartido(id: number) {
    // Lógica para eliminar el partido
    this.partidoService.deletePartido(id).subscribe(response => {
      console.log('Partido eliminado', response);
      this.loadPartidos(); // Recargar los partidos después de eliminar
    });
  }

  agregarPartido() {
    // Lógica para agregar un nuevo partido
    this.partidoService.createPartido(this.nuevoPartido).subscribe(response => {
      console.log('Partido agregado', response);
      this.loadPartidos(); // Recargar los partidos después de agregar
      this.nuevoPartido = { // Reiniciar el formulario
        nombre: '',
        fecha: '',
        hora: '',
        paisLocal: '',
        paisVisitante: '',
        goles_pais_local: null,
        goles_pais_visitante: null
      };
    });
  }

  getFlagUrl(pais: string): string {
    const countryNameCorrections: { [key: string]: string } = {
      'Brasil': 'Brazil'
    };
    
    const correctedCountryName = countryNameCorrections[pais] || pais;

    const countryCode = countries.getAlpha2Code(correctedCountryName, 'en');
    if (!countryCode) {
      return '';
    }
    return `https://flagcdn.com/w320/${countryCode.toLowerCase()}.png`;
  }
}
