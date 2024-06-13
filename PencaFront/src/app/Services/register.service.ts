import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { GlobalService } from '../../global.service';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private api_url = this.globalService.API_URL;

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  register(id: number, nombre: string, apellido: string, email: string, carrera: number, password: string, campeon: string, subcampeon: string): Observable<any> {
    console.log('Registering user', nombre);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = JSON.stringify({
      id: id,
      nombre: nombre,
      apellido: apellido,
      email: email,
      carrera: carrera,
      password: password,
      campeon: campeon,
      subcampeon: subcampeon
    });

    console.log('Registering user', body)

    return this.http.post<any>(this.api_url+"/login/register", body, { headers: headers })
      .pipe(
        map(response => {
          // Manejar la respuesta y guardar el token en localStorage
          if (response) {
            console.log('User registered', response);
            // localStorage.setItem('token', response.body);
          }
          return response;
        }),
        catchError(error => {
          console.error(error);
          throw new Error('Error al crear usuario');
        })
      );
  }
}
