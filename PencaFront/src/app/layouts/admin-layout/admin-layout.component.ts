import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent {
  title = 'PencFront';
  navigation = [
    { name: 'Partidos', href: '#', current: true, icon: 'home' },
    { name: 'Paises', href: '#', current: false, icon: 'settings' },
    { name: 'Carreras', href: '#', current: false, icon: 'notifications' },
  ];
  isMenuOpen = false;
}
