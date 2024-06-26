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
import { ValidGuard } from './auth/valid.guard';


// AuthGuard solo permite entrada a los que son users (no admin)
// AdminGuard solo permite entrada a los que son admin
// ValidGuard permite entrada a user y admin mientras tengan un token valido
// Si no tienen un token valido, o no tienen acceso a esa pagina se los redirige a /login (a checkear)
const routes: Routes = [
  { 
    path: '',
    component: LoginComponent
  },
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
    canActivate: [AuthGuard], //esta mal, era un ejemplo
    component: UserComponent
  },
  {
    path: 'posiciones',
    canActivate: [ValidGuard],
    component: PosicionesComponent
  },
  {
    path: 'partidos',
    canActivate: [ValidGuard],
    component: PartidosComponent
  },
  { path: '**', redirectTo: '', pathMatch: 'full' }  // Ruta de fallback
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
