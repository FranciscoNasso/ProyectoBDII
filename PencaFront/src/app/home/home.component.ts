import { Component, OnInit, OnDestroy } from '@angular/core';
import { PartidoService } from '../Services/partido/partido.service';
import * as countries from 'i18n-iso-countries';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  partidos: any[] = [];
  errores: { [key: number]: { local: boolean, visitante: boolean } } = {};
  translateX = 0;
  private intervalId: any;
  private currentIndex = 0;
  private itemsPerSlide = 3;

  constructor(private partidoService: PartidoService) {
    countries.registerLocale(require('i18n-iso-countries/langs/es.json'));
  }

  ngOnInit() {
    this.loadPartidos();
    this.startCarousel();
    this.updateItemsPerSlide();
    window.addEventListener('resize', this.updateItemsPerSlide.bind(this));
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
    window.removeEventListener('resize', this.updateItemsPerSlide.bind(this));
  }

  loadPartidos() {
    this.partidoService.getPartidos().subscribe((data: any[]) => {
      this.partidos = data;
      this.partidos.forEach((_, index) => {
        this.errores[index] = { local: false, visitante: false };
      });
    });
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

  startCarousel() {
    this.intervalId = setInterval(() => {
      this.currentIndex++;
      if (this.currentIndex * this.itemsPerSlide >= this.partidos.length) {
        this.currentIndex = 0;
      }
      this.translateX = -this.currentIndex * 100;
    }, 3000);
  }

  updateItemsPerSlide() {
    if (window.innerWidth < 768) {
      this.itemsPerSlide = 1;
    } else {
      this.itemsPerSlide = 3;
    }
  }

  difHoras(fecha: string, hora: string): number {
    const fechaPartido = new Date(`${fecha}T${hora}:00`);
    const ahora = new Date();
    return (fechaPartido.getTime() - ahora.getTime()) / 3600000;
  }
}