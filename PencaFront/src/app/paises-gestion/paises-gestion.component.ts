import { Component } from '@angular/core';
import { PaisService } from '../Services/pais/pais.service';

@Component({
  selector: 'app-paises-gestion',
  templateUrl: './paises-gestion.component.html',
  styleUrls: ['./paises-gestion.component.css']
})
export class PaisesGestionComponent {
  paises: any[] = [];

  constructor(private paisService: PaisService) { }
  
  ngOnInit() {
    this.loadPaises();
  }
  
  loadPaises() {
    this.paisService.getPaises().subscribe((data: any[]) => {
      this.paises = data;
    });
  }
}
