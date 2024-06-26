import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GlobalService } from 'src/global.service';
import { RegisterService } from '../Services/register/register.service';
import { CarreraService } from '../Services/carrera/carrera.service';
import { PaisService } from '../Services/pais/pais.service';
import { catchError, map } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  carreras: any[] = [];
  paises: any[] = [];

  constructor(private fb: FormBuilder, private carreraService: CarreraService, private paisService: PaisService, private globalService: GlobalService, private router: Router, private registerService: RegisterService) {
    this.registerForm = this.fb.group({
      documento: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      carrera: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      campeon: ['', [Validators.required]],
      subCamepon: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  ngOnInit() {
    this.loadCarreras();
    this.loadPaises();
  }

  loadCarreras() {
    this.carreraService.getCarreras().pipe(
      map((data) => {
        this.carreras = data;
      }),
      catchError((error) => {
        console.error('Error loading carreras', error);
        return of(null); // Retornar un observable con un valor nulo para continuar el flujo
      })
    ).subscribe();
  }

  loadPaises() {
    this.paisService.getPaises().subscribe((data: any[]) => {
      this.paises = data;
    });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  async onSubmit(): Promise<void> {
    const { documento, nombre, apellido, email, carrera, campeon, subCamepon, password } = this.registerForm.value;
    const idCarrera = parseInt(carrera);
    
    console.log(`Attempting to register user: ${nombre}`);


    this.registerService.register(documento, nombre, apellido, email, carrera, password, campeon, subCamepon).subscribe({
      next: (response) => {
        console.log('Register successful', response);
        this.router.navigate(['/partidos']);
      },
      error: (error) => {
        console.error('Register failed', error);
        alert('Falló el registro, intente nuevamente.');
      }
    });
  }
}
