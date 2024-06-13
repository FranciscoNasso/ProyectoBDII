import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { GlobalService } from '../../global.service';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = this.globalService.API_URL;  // URL de la API de login

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  login(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = JSON.stringify({
      ci: username,
      contrasenia: password
    });

    return this.http.post<any>(this.loginUrl+"/login/find/"+username, body, { headers: headers })
      .pipe(
        map(response => {
          // Manejar la respuesta y guardar el token en localStorage
          if (response) {
            localStorage.setItem('token', response.token);
          }
          console.log(response);
          return response;
        }),
        catchError(error => {
          console.error(error);
          throw new Error('Invalid credentialsservicets');
        })
      );
  }
}
