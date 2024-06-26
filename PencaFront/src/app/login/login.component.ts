import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/global.service';
import { LoginService } from '../Services/login/login.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  registerForm: FormGroup;

  constructor(private loginService: LoginService, private globalService: GlobalService, private router: Router, private fb: FormBuilder) {
    this.registerForm = this.fb.group({
      ci: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  async onSubmit(): Promise<void> {
    const { ci, password } = this.registerForm.value;

    console.log(`Attempting to login with ci: ${ci}`);

    this.loginService.login(ci, password).subscribe({
      next: (response) => {
          console.log('Login successful', response.token);
          // this.router.navigate(['/home']);
          localStorage.setItem('token', response.token);
          localStorage.setItem('id_user', ci);
          // alert('Bienvenido!');
          this.router.navigate(['partidos']);
      },
      error: (error) => {
          console.error('Login failed', error);
          alert('Invalid credentials');
      }
  });
  }
}
