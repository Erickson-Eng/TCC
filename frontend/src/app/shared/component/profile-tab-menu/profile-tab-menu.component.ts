import { Component, Input } from '@angular/core';
import { TabRoutes } from '../tab/model/tabRoutes';

@Component({
  selector: 'app-profile-tab-menu',
  templateUrl: './profile-tab-menu.component.html',
  styleUrls: ['./profile-tab-menu.component.scss']
})
export class ProfileTabMenuComponent {

  @Input() routes: TabRoutes[] = [];
  @Input() tabName: string  = '';

}
