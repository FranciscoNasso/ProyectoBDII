import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PartidosComponent } from './partidos/partidos.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UserComponent } from './user/user.component';
import { PosicionesComponent } from './posiciones/posiciones.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { PartidosGestionComponent } from './partidos-gestion/partidos-gestion.component';
import { CarreraGestionComponent } from './carrera-gestion/carrera-gestion.component';
import { PaisesGestionComponent } from './paises-gestion/paises-gestion.component';
import { NavComponent } from './layouts/nav/nav.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    PartidosComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    PosicionesComponent,
    MainLayoutComponent,
    AdminLayoutComponent,
    PartidosGestionComponent,
    CarreraGestionComponent,
    PaisesGestionComponent,
    NavComponent
  ],
  imports: [
    FormsModule,  
    RouterModule.forRoot([
      { path: '', component: LoginComponent },
      { path: 'home', component: HomeComponent },
      { path: 'partidos', component: PartidosComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'login', component: LoginComponent }
    ]),
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule, 
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true,
    }),
  ],
  exports: [
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
