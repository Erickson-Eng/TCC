import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GymRoutingModule } from './gym-routing.module';
import { InfoComponent } from './info/info.component';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { ComponentModule } from 'src/app/shared/component/component.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    InfoComponent
  ],
  imports: [
    CommonModule,
    GymRoutingModule,
    AppMaterialModule,
    ComponentModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class GymModule { }
