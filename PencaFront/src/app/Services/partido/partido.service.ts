import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalService } from '../../../global.service';
import { catchError, map } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PartidoService {
  private apiUrl = this.globalService.API_URL; 

  constructor(private http: HttpClient, private globalService: GlobalService) { }

  getPartidos(): Observable<any> {
    return this.http.get<any>(this.apiUrl + "/partido/findall").pipe(
      map((response) => { // Explicitly specify the type of 'response'
        return response;
      }),
      catchError((error) => {
        console.error('Error loading carreras', error);
        return of(null); // Retornar un observable con un valor nulo para continuar el flujo
      })
    );
  }

  addGoles(partido: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/partido/load-score/${partido.id}`, partido).pipe(
      map((response) => {
        return response;
      }),
      catchError((error) => {
        console.error('Error loading score', error);
        return of(null);
      })
    );
  }

  deletePartido(partidoId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/partido/delete/${partidoId}`).pipe(
      map((response) => {
        return response;
      }),
      catchError((error) => {
        console.error('Error deleting partido', error);
        return of(null);
      })
    );
  }

  createPartido(partido: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/partido/save`, partido).pipe(
      map((response) => {
        return response;
      }),
      catchError((error) => {
        console.error('Error creating partido', error);
        return of(null);
      })
    );
  }
}
