import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileTabMenuComponent } from './profile-tab-menu.component';

describe('ProfileTabMenuComponent', () => {
  let component: ProfileTabMenuComponent;
  let fixture: ComponentFixture<ProfileTabMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileTabMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileTabMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
