import { Routes } from '@angular/router';
import { PartidosComponent } from './partidos/partidos.component';

export const routes: Routes = [
    { path: 'partidos', component: PartidosComponent },
    { path: '', redirectTo: '/partidos', pathMatch: 'full' }
];
