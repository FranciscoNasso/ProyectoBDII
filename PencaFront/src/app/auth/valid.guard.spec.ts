import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../Services/auth/auth.service';

import { ValidGuard } from './valid.guard';
import { of } from 'rxjs';

describe('ValidGuard', () => {
  let validGuard: ValidGuard;
  let authService: AuthService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AuthService, ValidGuard]
    });

    validGuard = TestBed.inject(ValidGuard);
    authService = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(validGuard).toBeTruthy();
  });

  it('should call canActivate and return an observable', () => {
    const canActivateSpy = spyOn(validGuard, 'canActivate');
    const isAuthenticatedSpy = spyOn(authService, 'isAuthenticated').and.returnValue(of(true));

    validGuard.canActivate();

    expect(canActivateSpy).toHaveBeenCalledTimes(1);
    expect(isAuthenticatedSpy).toHaveBeenCalledTimes(1);
  });
});