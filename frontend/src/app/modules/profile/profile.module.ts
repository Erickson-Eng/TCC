import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ProfileRoutingModule } from './profile-routing.module';
import { AddressComponent } from './address/address.component';
import { AppMaterialModule } from '../../shared/app-material/app-material.module';
import { AthleticComponent } from './athletic/athletic.component';
import { PersonalComponent } from './personal/personal.component';
import { ComponentModule } from '../../shared/component/component.module';

@NgModule({
  declarations: [AddressComponent, AthleticComponent, PersonalComponent],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    AppMaterialModule,
    ComponentModule,
    FormsModule,
    ReactiveFormsModule
  ],
})
export class ProfileModule {}
