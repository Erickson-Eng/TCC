import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AthleticComponent } from './athletic.component';

describe('AthleticComponent', () => {
  let component: AthleticComponent;
  let fixture: ComponentFixture<AthleticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AthleticComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AthleticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
