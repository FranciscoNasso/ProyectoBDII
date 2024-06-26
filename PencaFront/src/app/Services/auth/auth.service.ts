import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/usuario/validarToken';

  constructor(private http: HttpClient, private router: Router) { }

  validateToken(token: string): Observable<string> {
    return this.http.post(this.apiUrl, token, { responseType: 'text' });
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): Observable<boolean> {
    const token = this.getToken();
    if (!token) {
      return of(false);
    }
    return this.validateToken(token).pipe(
      map(response => response !== 'invalido' && response !== 'token_expirado')
    );
  }

  isAdmin(): Observable<boolean> {
    const token = this.getToken();
    if (!token) {
      return of(false);
    }
    return this.validateToken(token).pipe(
      map(response => response === 'admin')
    );
  }
}
