import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../Services/auth/auth.service';

import { AuthGuard } from './auth.guard';
import { of } from 'rxjs';

describe('AuthGuard', () => {
  let authGuard: AuthGuard;
  let authService: AuthService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AuthService, AuthGuard]
    });

    authGuard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(authGuard).toBeTruthy();
  });

  it('should call canActivate and return an observable', () => {
    const canActivateSpy = spyOn(authGuard, 'canActivate');
    const isAuthenticatedSpy = spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

    authGuard.canActivate();

    expect(canActivateSpy).toHaveBeenCalledTimes(1);
    expect(isAuthenticatedSpy).toHaveBeenCalledTimes(1);
  });
});