import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalService } from '../../../global.service';
import { catchError, map } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PrediccionService {
  private apiUrl = this.globalService.API_URL; 

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  setPrediccion(id_partido: string, golesLocal: string, golesVisitante: string, id_participante: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = JSON.stringify({
      id_partido: id_partido,
      goles_pais_local: golesLocal,
      goles_pais_visitante: golesVisitante,
      id_participante: id_participante
    });
    console.log(body)
    return this.http.post<any>(this.apiUrl+"/prediccion/save", body, { headers: headers })
    .pipe(
      map(response => {
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
