import { Component, OnInit } from '@angular/core';
import { PosicionesService } from '../Services/posiciones/posiciones.service';

@Component({
  selector: 'app-posiciones',
  templateUrl: './posiciones.component.html',
  styleUrls: ['./posiciones.component.css']
})
export class PosicionesComponent implements OnInit {
  posiciones: { id_participante: number, puntos: number, nombre: string, apellido: string }[] = [];
  id_usuario: string = "";

  constructor(private posicionesService: PosicionesService) { }

  ngOnInit() {
    this.posicionesService.getRankingFinal().subscribe(data => {
      if (data.length > 0) {
        this.posiciones = data.sort((a: any, b: any) => b.puntos - a.puntos);
      } else {
        this.posicionesService.getRanking().subscribe(data => {
          this.posiciones = data.sort((a: any, b: any) => b.puntos - a.puntos);
        });
      }
    });
    const dataStorage = localStorage.getItem('id_user');
    if (dataStorage) {
      this.id_usuario = dataStorage;
    }
  }

  getBackgroundClass(index: number, id_participante: number): string {
    if (index === 0) {
      return 'bg-gradient-to-r from-yellow-400 to-yellow-600 text-white';
    } else if (index === 1) {
      return 'bg-gradient-to-r from-gray-400 to-gray-600 text-white';
    } else if (index === 2) {
      return 'bg-gradient-to-r from-yellow-800 to-yellow-900 text-white';
    } else if (id_participante.toString() === this.id_usuario) {
      return 'bg-gradient-to-r from-[#307cdf] to-[#0852b4] text-white hover:from-[#0852b4] hover:to-[#307cdf]';
    } else {
      return 'bg-gray-200 hover:bg-gray-300';
    }
  }
}
