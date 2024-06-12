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

  async onSubmit(): Promise<void> {
    const { username, password } = this.registerForm.value;

    console.log(`Attempting to login with username: ${username}`);

    this.loginService.login(username, password).subscribe({
      next: (response) => {
          console.log('Login successful', response);
          // this.router.navigate(['/home']);
          alert('Valid credentials');
      },
      error: (error) => {
          console.error('Login failed', error);
          alert('Invalid credentials');
      }
  });
  }
}
