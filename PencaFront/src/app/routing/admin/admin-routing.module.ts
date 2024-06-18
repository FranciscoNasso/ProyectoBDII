import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayoutComponent } from '../../layouts/admin-layout/admin-layout.component';
import { PartidosGestionComponent } from 'src/app/partidos-gestion/partidos-gestion.component';
import { CarreraGestionComponent } from 'src/app/carrera-gestion/carrera-gestion.component';
import { PaisesGestionComponent } from 'src/app/paises-gestion/paises-gestion.component';

const routes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      { path: 'partidos', component: PartidosGestionComponent },
      { path: "carreras", component: CarreraGestionComponent },
      { path: "paises", component: PaisesGestionComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
