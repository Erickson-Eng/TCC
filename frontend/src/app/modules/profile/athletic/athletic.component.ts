import { Component } from '@angular/core';
import { TabRoutes } from '../../../shared/component/tab/model/tabRoutes';

@Component({
  selector: 'app-athletic',
  templateUrl: './athletic.component.html',
  styleUrls: ['./athletic.component.scss'],
})
export class AthleticComponent {
  rota = 'Athletic info';

  routes: TabRoutes[] = [
    { name: 'personal', route: '/profile/personal' },
    { name: 'address', route: '/profile/address' },
    { name: 'athletic', route: '/profile/athletic' },
  ];
}
