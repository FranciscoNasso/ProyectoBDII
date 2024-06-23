import { Component, OnInit } from '@angular/core';
import { PaisService } from '../Services/pais/pais.service';
import * as countries from 'i18n-iso-countries';

countries.registerLocale(require('i18n-iso-countries/langs/es.json'));

@Component({
  selector: 'app-paises-gestion',
  templateUrl: './paises-gestion.component.html',
  styleUrls: ['./paises-gestion.component.css']
})
export class PaisesGestionComponent implements OnInit {
  paises: any[] = [];
  isModalOpen = false;
  modalTitle = '';
  modalButtonText = '';
  currentPais: any = { nombre: '' };

  constructor(private paisService: PaisService) { }
  
  ngOnInit() {
    this.loadPaises();
  }
  
  loadPaises() {
    this.paisService.getPaises().subscribe((data: any[]) => {
      this.paises = data;
    });
  }

  getFlagUrl(pais: string): string {
    const countryCode = countries.getAlpha2Code(pais, 'es');
    if (!countryCode) {
      return '';
    }
    return `https://hatscripts.github.io/circle-flags/flags/${countryCode.toLowerCase()}.svg`;
  }

  openAddModal() {
    this.modalTitle = 'Agregar País';
    this.modalButtonText = 'Agregar';
    this.currentPais = { nombre: '' };
    this.isModalOpen = true;
  }

  openEditModal(pais: any) {
    this.modalTitle = 'Editar País';
    this.modalButtonText = 'Guardar';
    this.currentPais = { ...pais };
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  onSubmit() {
    if (this.modalTitle === 'Agregar País') {
      this.paisService.addPais(this.currentPais).subscribe(() => {
        this.loadPaises();
        this.closeModal();
      });
    } else {
      this.paisService.updatePais(this.currentPais).subscribe(() => {
        this.loadPaises();
        this.closeModal();
      });
    }
  }
}
