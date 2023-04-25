import { Component } from '@angular/core';
import { TabRoutes } from '../../../shared/component/tab/model/tabRoutes';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss'],
})
export class AddressComponent {
  rota = 'Address info';

  routes: TabRoutes[] = [
    { name: 'personal', route: '/profile/personal' },
    { name: 'address', route: '/profile/address' },
    { name: 'athletic', route: '/profile/athletic' },
  ];
}
