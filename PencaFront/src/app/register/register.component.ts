import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GlobalService } from 'src/global.service';
import { RegisterService } from '../Services/register.service';
import { CarreraService } from '../Services/carrera.service';
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

  constructor(private fb: FormBuilder, private carreraService: CarreraService, private globalService: GlobalService, private router: Router, private registerService: RegisterService) {
    this.registerForm = this.fb.group({
      nombre: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      carrera: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  ngOnInit() {
    this.loadCarreras();
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

  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  async onSubmit(): Promise<void> {
    const { nombre, email, carrera, password, confirmPassword } = this.registerForm.value;

    console.log(`Attempting to register user: ${nombre}`);

    this.registerService.register( /* ci-> */1234, nombre, /* apellido-> */"añañin", email, carrera, password, /* campeon-> */"Uruguay", /* subcampeon-> */"Argentina").subscribe({
      next: (response) => {
        console.log('Register successful', response);
        // this.router.navigate(['/home']);
      },
      error: (error) => {
        console.error('Register failed', error);
        alert('Falló el registro, intente nuevamente.');
      }
    });
  }
}
