import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent {
  title = 'PencFront';
  isSidebarOpen = false;

  navigation = [
    { name: 'Partidos', href: 'app/partidos', current: true, icon: 'fa-regular fa-futbol' },
    { name: 'Paises', href: 'app/paises', current: false, icon: 'fa-solid fa-earth-americas' },
    { name: 'Competicion', href: 'app/competicion', current: false, icon: 'fa-solid fa-trophy' },
  ];

  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  logout() {
    localStorage.removeItem('id_user');
    localStorage.removeItem('token');
  }
}
