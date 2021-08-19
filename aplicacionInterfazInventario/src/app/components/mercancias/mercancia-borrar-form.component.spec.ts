import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MercanciaBorrarFormComponent } from './mercancia-borrar-form.component';

describe('MercanciaBorrarFormComponent', () => {
  let component: MercanciaBorrarFormComponent;
  let fixture: ComponentFixture<MercanciaBorrarFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MercanciaBorrarFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MercanciaBorrarFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
