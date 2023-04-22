import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AccountRoutingModule } from './account-routing.module';
import { CreateAccountComponent } from './create-account/create-account.component';
import { AppMaterialModule } from '../../shared/app-material/app-material.module';
import { RecoveryAccountComponent } from './recovery-account/recovery-account.component';


@NgModule({
  declarations: [
    CreateAccountComponent,
    RecoveryAccountComponent
  ],
  imports: [
    CommonModule,
    AccountRoutingModule,
    AppMaterialModule
  ]
})
export class AccountModule { }
