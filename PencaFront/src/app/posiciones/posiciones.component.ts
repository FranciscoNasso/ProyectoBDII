import { Component } from '@angular/core';
import { PosicionesService } from '../Services/posiciones/posiciones.service';

@Component({
  selector: 'app-posiciones',
  templateUrl: './posiciones.component.html',
  styleUrls: ['./posiciones.component.css']
})
export class PosicionesComponent {

  /* posiciones: { nombre: string, puntos: number }[] = [
    { nombre: 'Facundo Hernandez', puntos: 10 },
    { nombre: 'Equipo 2', puntos: 8 },
    { nombre: 'Equipo 3', puntos: 6 },
    { nombre: 'Equipo 4', puntos: 4 },
    { nombre: 'Equipo 5', puntos: 2 }
  ]; */

  posiciones: { id_participante: number, puntos: number, nombre: string, apellido: string }[] = [];

  constructor(private posicionesService: PosicionesService) { }
  id_usuario: string = "";

  ngOnInit() {
    this.posicionesService.getRanking().subscribe(data => {
      this.posiciones = data;
    });
    const dataStorage = localStorage.getItem('id_usuario');
    if (dataStorage) {
      this.id_usuario = dataStorage;
    }
  }
}
