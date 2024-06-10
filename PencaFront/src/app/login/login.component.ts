import { Component } from '@angular/core';
import { RegisterComponent } from '../register/register.component';
import { Router } from '@angular/router';
import { GlobalService } from 'src/global.service';
import { LoginService } from '../Services/login.service';
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
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    this.loginService.login(this.registerForm.value.username, this.registerForm.value.password).subscribe(
      response => {
        // Navegar a la página de inicio
        console.log('Login successful', response);
        //this.router.navigate(['/home']);
      },
      error => {
        console.error(error);
        alert('Invalid credentials');
      }
    );
  }
}
