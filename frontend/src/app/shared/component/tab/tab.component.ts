import { Component, Input } from '@angular/core';
import { TabRoutes } from './model/tabRoutes';

@Component({
  selector: 'app-tab',
  templateUrl: './tab.component.html',
  styleUrls: ['./tab.component.scss']
})
export class TabComponent {

  @Input() routes: TabRoutes[] = [];
}
