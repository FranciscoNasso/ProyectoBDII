import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  currentRoute: string = '';
  navigation = [
    { name: 'Partidos', href: '/partidos', current: false },
    { name: 'Posiciones', href: '/posiciones', current: false },
    { name: 'User', href: '/user', current: false, icon: './assets/user.png' },
  ];
  isMenuOpen = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = event.urlAfterRedirects;
        this.updateNavigation();
      }
    });
  }

  updateNavigation(): void {
    this.navigation.forEach(item => {
      item.current = (item.href === this.currentRoute);
    });
  }

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
