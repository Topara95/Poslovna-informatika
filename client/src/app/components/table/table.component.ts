import { Component, OnInit, Input } from '@angular/core';
import { TableInfo } from '../../models/tableInfo';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() tableInfo: TableInfo;

  constructor() { }

  ngOnInit() {
  }

}
