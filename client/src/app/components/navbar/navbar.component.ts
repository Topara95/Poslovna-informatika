import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';

import { TableRoute, Relacija } from '../../models/tableInfo';
import { TableRoutes } from '../../models/data';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  tableRoutes: TableRoute[];
  @Input() preci: Relacija[];
  @Input() potomci: Relacija[];
  @Output() first: EventEmitter<any> = new EventEmitter();
  @Output() previous: EventEmitter<any> = new EventEmitter();
  @Output() next: EventEmitter<any> = new EventEmitter();
  @Output() last: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.tableRoutes = TableRoutes;
  }

}
