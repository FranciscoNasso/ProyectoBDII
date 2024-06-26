import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PartidosComponent } from './partidos/partidos.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PosicionesComponent } from './posiciones/posiciones.component';
import { UserComponent } from './user/user.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminGuard } from './auth/admin.guard';


// falta agregar rutas para usuarios y para admins
const routes: Routes = [
  { path: '', loadChildren: () => import('./routing/main/main.module').then(m => m.MainModule) },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'user',
    canActivate: [AuthGuard],
    component: UserComponent
  },
  {
    path: 'posiciones',
    canActivate: [AuthGuard],
    component: PosicionesComponent
  },
  {
    path: 'partidos',
    canActivate: [AdminGuard],
    component: PartidosComponent
  },
  { path: 'app', loadChildren: () => import('./routing/admin/admin.module').then(m => m.AdminModule) },
  { path: '**', redirectTo: '', pathMatch: 'full' }  // Ruta de fallback
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
