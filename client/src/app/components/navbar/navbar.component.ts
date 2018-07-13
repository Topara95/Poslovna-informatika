import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material';

import { TableRoute, Relacija } from '../../models/tableInfo';
import { TableRoutes } from '../../models/data';
import { IzvodKlijentaComponent } from '../izvod-klijenta/izvod-klijenta.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  tableRoutes: TableRoute[];
  fizickaLica: boolean;
  banka: boolean;
  @Input() highlighted: number;
  @Input() import: string;
  @Input() export: string;
  @Input() preci: Relacija[];
  @Input() potomci: Relacija[];
  @Output() first: EventEmitter<any> = new EventEmitter();
  @Output() previous: EventEmitter<any> = new EventEmitter();
  @Output() next: EventEmitter<any> = new EventEmitter();
  @Output() last: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient, private route: ActivatedRoute, private dialog: MatDialog) { }

  ngOnInit() {
    this.tableRoutes = TableRoutes;

    if (this.route.snapshot.routeConfig.path === 'fizickaLica') {
      this.fizickaLica = true;
    } else {
      this.fizickaLica = false;
    }

    if (this.route.snapshot.routeConfig.path === 'banke') {
      this.banka = true;
    } else {
      this.banka = false;
    }
  }

  onFileSelected(event: any) {
    const selectedFile = <File>event.target.files[0];

    const formData = new FormData();
    formData.append('file', selectedFile);

    this.http.post<any>(this.import, formData)
      .subscribe(() => alert('XML importovan'));
  }

  onPretraga() {
    this.dialog.open(IzvodKlijentaComponent, {
      data: this.highlighted
    });
  }

}
