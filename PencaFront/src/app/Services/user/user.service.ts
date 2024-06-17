import { HttpClient } from '@angular/common/http';
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
}
