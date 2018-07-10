import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { HttpClient } from '@angular/common/http';

import { TableInfo } from '../../models/tableInfo';

@Component({
  selector: 'app-zoom-table',
  templateUrl: './zoom-table.component.html',
  styleUrls: ['./zoom-table.component.css']
})
export class ZoomTableComponent implements OnInit {

  selectedRow = -1;
  podaci = [];

  constructor(@Inject(MAT_DIALOG_DATA) public data: TableInfo, private http: HttpClient) { }

  ngOnInit() {
    this.http.get<any>(this.data.podaciUrl)
      .subscribe(podaci => this.podaci = podaci);
  }

}
