import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalService } from '../../../global.service';
import { catchError, map } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PosicionesService {
  private apiUrl = this.globalService.API_URL; 

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  getPaises(): Observable<any> {
    return this.http.get<any>(this.apiUrl + "/pais/findall").pipe(
      map((response) => { // Explicitly specify the type of 'response'
        return response;
      }),
      catchError((error) => {
        console.error('Error loading carreras', error);
        return of(null); // Retornar un observable con un valor nulo para continuar el flujo
      })
    );
  }

  getRanking(): Observable<any> {
    return this.http.get<any>(this.apiUrl + "/prediccion/getRanking").pipe(
      map((response) => { // Explicitly specify the type of 'response'
        return response;
      }),
      catchError((error) => {
        console.error('Error loading ranking', error);
        return of(null); // Retornar un observable con un valor nulo para continuar el flujo
      })
    );
  }

  getRankingFinal(): Observable<any> {
    return this.http.get<any>(this.apiUrl + "/administrador/getRankingFinal").pipe(
      map((response) => { // Explicitly specify the type of 'response'
        return response;
      }),
      catchError((error) => {
        console.error('Error loading ranking', error);
        return of(null); // Retornar un observable con un valor nulo para continuar el flujo
      })
    );
  }
}
