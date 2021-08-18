import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MercanciaFormComponent } from './mercancia-form.component';

describe('MercanciaFormComponent', () => {
  let component: MercanciaFormComponent;
  let fixture: ComponentFixture<MercanciaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MercanciaFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MercanciaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
