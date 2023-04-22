import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabComponent } from './tab/tab.component';

import { AppMaterialModule } from '../app-material/app-material.module';
import { RouterModule } from '@angular/router';
import { CardComponent } from './card/card.component';
import { UploadImageComponent } from './upload-image/upload-image.component';
import { ProfileTabMenuComponent } from './profile-tab-menu/profile-tab-menu.component';


@NgModule({
  exports: [
    TabComponent,
    UploadImageComponent,
    ProfileTabMenuComponent
  ],
  declarations: [
    TabComponent,
    CardComponent,
    UploadImageComponent,
    ProfileTabMenuComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    RouterModule
  ]
})
export class ComponentModule { }
