import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartidosGestionComponent } from './partidos-gestion.component';

describe('PartidosGestionComponent', () => {
  let component: PartidosGestionComponent;
  let fixture: ComponentFixture<PartidosGestionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PartidosGestionComponent]
    });
    fixture = TestBed.createComponent(PartidosGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
