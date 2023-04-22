import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from './address/address.component';
import { AthleticComponent } from './athletic/athletic.component';
import { PersonalComponent } from './personal/personal.component';

const routes: Routes = [
  {path: 'address', component:AddressComponent},
  {path: 'athletic', component:AthleticComponent},
  {path: 'personal', component:PersonalComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
