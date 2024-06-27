import { Component, OnInit } from '@angular/core';
import { PartidoService } from '../Services/partido/partido.service';
import { PrediccionService } from '../Services/prediccion/prediccion.service';
import * as countries from 'i18n-iso-countries';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-partidos',
  templateUrl: './partidos.component.html',
  styleUrls: ['./partidos.component.css']
})
export class PartidosComponent implements OnInit {
  title = 'Partidos';
  partidos: any[] = [];
  errores: { [key: number]: { local: boolean, visitante: boolean } } = {};

  constructor(private partidoService: PartidoService, private prediccionService: PrediccionService, private toastr: ToastrService) {
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
    const idUser = localStorage.getItem('id_user');
    if (idUser !== null) {
      this.partidoService.getPartidosByUser(idUser).subscribe((data: any[]) => {
        console.log('Partidos', data);
        this.partidos = data;
        this.partidos.forEach((_, index) => {
          this.errores[index] = { local: false, visitante: false };
        });
      });
    } else {
      this.partidoService.getPartidos().subscribe((data: any[]) => {
        console.log('Partidos', data);
        this.partidos = data;
        this.partidos.forEach((_, index) => {
          this.errores[index] = { local: false, visitante: false };
        });
      });
    }
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
        this.toastr.success('Predicción guardada correctamente', 'Éxito'); // Mostrar toast aquí
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

  calcularPuntaje(partido: any): string {
    if (partido.goles_pais_local === null || partido.goles_pais_visitante === null) {
      return '';
    }

    // Check if the user predicted the correct number of goals for each team
    const acertoGolesLocal = partido.prediccion_pais_local === partido.goles_pais_local;
    const acertoGolesVisitante = partido.prediccion_pais_visitante === partido.goles_pais_visitante;

    if (acertoGolesLocal && acertoGolesVisitante) {
      // User predicted the correct number of goals for both teams
      return '+4 pts';
    }

    let puntaje = 0;

    // Determine the winner or if it's a draw
    let resultado = '';
    if (partido.goles_pais_local > partido.goles_pais_visitante) {
      resultado = 'local'; // Local team wins
    } else if (partido.goles_pais_local < partido.goles_pais_visitante) {
      resultado = 'visitante'; // Visitor team wins
    } else {
      resultado = 'empate'; // It's a draw
    }

    // Check if the user predicted the correct winner or draw
    const acertoLocal = partido.prediccion_pais_local !== null && partido.prediccion_pais_local > partido.prediccion_pais_visitante;
    const acertoVisitante = partido.prediccion_pais_visitante !== null && partido.prediccion_pais_local < partido.prediccion_pais_visitante;
    const acertoEmpate = partido.prediccion_pais_local === partido.prediccion_pais_visitante;

    if ((resultado === 'local' && acertoLocal) || (resultado === 'visitante' && acertoVisitante) || (resultado === 'empate' && acertoEmpate)) {
      // User predicted the winner or the draw correctly
      puntaje += 2;
    }

    return `+${puntaje} pts`;
  }
}
