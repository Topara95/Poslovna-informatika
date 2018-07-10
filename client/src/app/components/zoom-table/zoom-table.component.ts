import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

import { TableInfo } from '../../models/tableInfo';

@Component({
  selector: 'app-zoom-table',
  templateUrl: './zoom-table.component.html',
  styleUrls: ['./zoom-table.component.css']
})
export class ZoomTableComponent implements OnInit {

  selectedRow = -1;

  constructor(@Inject(MAT_DIALOG_DATA) public data: TableInfo) { }

  ngOnInit() {
  }

}
