import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PartidosComponent } from './partidos/partidos.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PosicionesComponent } from './posiciones/posiciones.component';
import { UserComponent } from './user/user.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { PartidosGestionComponent } from './partidos-gestion/partidos-gestion.component';
import { CarreraGestionComponent } from './carrera-gestion/carrera-gestion.component';
import { PaisesGestionComponent } from './paises-gestion/paises-gestion.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminGuard } from './auth/admin.guard';
import { ValidGuard } from './auth/valid.guard';


// AuthGuard solo permite entrada a los que son users (no admin)
// AdminGuard solo permite entrada a los que son admin
// ValidGuard permite entrada a user y admin mientras tengan un token valido
// Si no tienen un token valido, o no tienen acceso a esa pagina se los redirige a /login (a checkear)
const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: '', component: HomeComponent, },
      { path: 'partidos', canActivate: [AuthGuard], component: PartidosComponent, },
      { path: 'posiciones', canActivate: [ValidGuard], component: PosicionesComponent, },
      { path: 'user', canActivate: [AuthGuard], component: UserComponent, }
    ]
  },
  {
    path: 'app',
    canActivate: [AdminGuard],
    component: AdminLayoutComponent,
    children: [
      { path: 'partidos', component: PartidosGestionComponent },
      { path: "carreras", component: CarreraGestionComponent },
      { path: "paises", component: PaisesGestionComponent },
    ]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
