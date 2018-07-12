import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { TableRoute, Relacija } from '../../models/tableInfo';
import { TableRoutes } from '../../models/data';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  tableRoutes: TableRoute[];
  @Input() import: string;
  @Input() export: string;
  @Input() preci: Relacija[];
  @Input() potomci: Relacija[];
  @Output() first: EventEmitter<any> = new EventEmitter();
  @Output() previous: EventEmitter<any> = new EventEmitter();
  @Output() next: EventEmitter<any> = new EventEmitter();
  @Output() last: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.tableRoutes = TableRoutes;
  }

  onFileSelected(event: any) {
    const selectedFile = <File>event.target.files[0];

    const formData = new FormData();
    formData.append('file', selectedFile);

    this.http.post<any>(this.import, formData)
      .subscribe(() => alert('XML importovan'));
  }

}
