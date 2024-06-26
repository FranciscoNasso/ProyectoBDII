import { Component, OnInit } from '@angular/core';
import { PartidoService } from '../Services/partido/partido.service';
import { PrediccionService } from '../Services/prediccion/prediccion.service';
import * as countries from 'i18n-iso-countries';

@Component({
  selector: 'app-partidos',
  templateUrl: './partidos.component.html',
  styleUrls: ['./partidos.component.css']
})
export class PartidosComponent implements OnInit {
  title = 'Partidos';
  partidos: any[] = [];
  errores: { [key: number]: { local: boolean, visitante: boolean } } = {};

  constructor(private partidoService: PartidoService, private prediccionService: PrediccionService) {
    countries.registerLocale(require('i18n-iso-countries/langs/es.json'));
  }

  ngOnInit() {
    this.loadPartidos();
  }

  habilitarPrediccion(dia: string, hora: string) {
    return this.difHoras(dia, hora) > 1;
  }

  difHoras(dia: string, hora: string) {
    const ahora = new Date();
    const horaPartido = new Date(dia + 'T' + hora);
    const diferenciaEnMilisegundos = horaPartido.getTime() - ahora.getTime();
    const diferenciaEnHoras = diferenciaEnMilisegundos / (1000 * 60 * 60);
    return diferenciaEnHoras;
  }

  loadPartidos() {
    this.partidoService.getPartidos().subscribe((data: any[]) => {
      this.partidos = data;
      this.partidos.forEach((_, index) => {
        this.errores[index] = { local: false, visitante: false };
      });
    });
  }

  predecir(index: number) {
    const partido = this.partidos[index]; 
    const localInput = document.getElementById(`local-${index}`) as HTMLInputElement;
    const visitanteInput = document.getElementById(`visitante-${index}`) as HTMLInputElement;
    const local = localInput.value;
    const visitante = visitanteInput.value;
    const idUser = localStorage.getItem('id_user');
    this.errores[index].local = !local;
    this.errores[index].visitante = !visitante;

    if (idUser !== null) {
      console.log(`Enviando datos: Local - ${local}, Visitante - ${visitante}`);
      this.prediccionService.setPrediccion(partido.id, local, visitante, idUser).subscribe((data) => {
        console.log('Prediccion realizada', data);
      });
    }
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
}
