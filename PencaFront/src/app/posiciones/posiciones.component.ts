import { Component } from '@angular/core';

@Component({
  selector: 'app-posiciones',
  templateUrl: './posiciones.component.html',
  styleUrls: ['./posiciones.component.css']
})
export class PosicionesComponent {
  posiciones: { nombre: string, puntos: number }[] = [
    { nombre: 'Facundo Hernandez', puntos: 10 },
    { nombre: 'Equipo 2', puntos: 8 },
    { nombre: 'Equipo 3', puntos: 6 },
    { nombre: 'Equipo 4', puntos: 4 },
    { nombre: 'Equipo 5', puntos: 2 }
  ];
  id_usuario: string = "";

  ngOnInit() {
    const dataStorage = localStorage.getItem('id_usuario');
    if (dataStorage) {
      this.id_usuario = dataStorage;
    }
  }
}
