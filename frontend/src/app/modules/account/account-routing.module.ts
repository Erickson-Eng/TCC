import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateAccountComponent } from './create-account/create-account.component';
import { RecoveryAccountComponent } from './recovery-account/recovery-account.component';

const routes: Routes = [
  { path: 'create', component:CreateAccountComponent},
  { path: 'recovery', component:RecoveryAccountComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
