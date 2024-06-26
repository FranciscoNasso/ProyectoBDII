import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../Services/auth/auth.service';
import { Observable, of } from 'rxjs';
import { concatMap, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(): Observable<boolean> {
    return this.authService.isAuthenticated().pipe(
      concatMap(isAuthenticated => {
        if (!isAuthenticated) {
          this.router.navigate(['/login']);
          return of(false);
        }
        return this.authService.isAdmin().pipe(
          map(isAdmin => {
            if (isAdmin) {
              this.router.navigate(['/login']);
              return false;
            }
            return true;
          })
        );
      })
    );
  }
}