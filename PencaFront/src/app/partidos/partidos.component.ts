import { Component } from '@angular/core';

@Component({
  selector: 'app-partidos',
  standalone: true,
  imports: [],
  templateUrl: './partidos.component.html',
  styleUrl: './partidos.component.css'
})
export class PartidosComponent {
  title = 'Partidos';
  
  partidos: any[] = [
    {
      fecha: '2021-06-13',
      partidos: [
        {
          hora: '18:00',
          local: 'Argentina',
          visitante: 'Chile',
          golesLocal: 1,
          golesVisitante: 1
        },
        {
          hora: '21:00',
          local: 'Paraguay',
          visitante: 'Bolivia',
          golesLocal: 3,
          golesVisitante: 1
        }
      ]
    },
    {
      fecha: '2021-06-14',
      partidos: [
        {
          hora: '18:00',
          local: 'Colombia',
          visitante: 'Ecuador',
          golesLocal: null,
          golesVisitante: null
        },
        {
          hora: '21:00',
          local: 'Brasil',
          visitante: 'Venezuela',
          golesLocal: null,
          golesVisitante: null
        }
      ]
    }
  ];
}
