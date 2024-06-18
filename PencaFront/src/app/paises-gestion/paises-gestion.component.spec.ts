import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaisesGestionComponent } from './paises-gestion.component';

describe('PaisesGestionComponent', () => {
  let component: PaisesGestionComponent;
  let fixture: ComponentFixture<PaisesGestionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PaisesGestionComponent]
    });
    fixture = TestBed.createComponent(PaisesGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
