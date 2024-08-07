import { Component, OnInit } from '@angular/core';
import { PaisService } from '../Services/pais/pais.service';
import * as countries from 'i18n-iso-countries';
import { ToastrService } from 'ngx-toastr';

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

  constructor(private paisService: PaisService, private toast: ToastrService) { }

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
    try {
      if (this.modalTitle === 'Agregar País') {
        this.paisService.addPais(this.currentPais).subscribe(() => {
          this.loadPaises();
          this.closeModal();
          this.toast.success('País agregado correctamente', 'País agregado');
        });
      } else {
        this.paisService.updatePais(this.currentPais).subscribe(() => {
          this.loadPaises();
          this.closeModal();
          this.toast.success('País agregado correctamente', 'País agregado');
        });
      }
    } catch (error) {
      this.toast.error('Ecurrio un error', 'Error');
    }
  }

  deletePais(nombre: string) {
    if (confirm('¿Estás seguro de que deseas eliminar este país?')) {
      this.paisService.deletePais(nombre).subscribe(() => {
        this.loadPaises();
      });
    }
  }
}
