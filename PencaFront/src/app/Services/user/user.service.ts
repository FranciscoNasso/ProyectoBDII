import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../../../global.service';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = this.globalService.API_URL;

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  getUserById(id: string): Observable<any> {
    return this.http.get(this.apiUrl + `/usuario/find/${id}`).pipe(
      catchError(error => {
        return throwError('User not found');
      })
    );
  }

  finalizarEvento(idCampeon:string, idSubCampeon: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const body = JSON.stringify({
      campeon: idCampeon,
      subcampeon: idSubCampeon
    });
    return this.http.post(this.apiUrl + '/administrador/finalizar', body, { headers: headers }).pipe(
      catchError(error => {
        return throwError('Error finalizando evento');
      })
    );
  }
}
