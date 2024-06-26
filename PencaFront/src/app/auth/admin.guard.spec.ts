import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../Services/auth/auth.service';

import { AdminGuard } from './admin.guard';
import { of } from 'rxjs';

describe('AdminGuard', () => {
  let adminGuard: AdminGuard;
  let authService: AuthService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AuthService, AdminGuard]
    });

    adminGuard = TestBed.inject(AdminGuard);
    authService = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(adminGuard).toBeTruthy();
  });

  it('should call canActivate and return an observable', () => {
    const canActivateSpy = spyOn(adminGuard, 'canActivate');
    const isAdminSpy = spyOn(authService, 'isAdmin').and.returnValue(of(true));

    adminGuard.canActivate();

    expect(canActivateSpy).toHaveBeenCalledTimes(1);
    expect(isAdminSpy).toHaveBeenCalledTimes(1);
  });
});