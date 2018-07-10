import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material';

import { environment } from '../../../environments/environment';
import { TableInfo } from '../../models/tableInfo';
import { ZoomTableComponent } from '../zoom-table/zoom-table.component';

@Component({
  selector: 'app-naseljena-mesta',
  templateUrl: './naseljena-mesta.component.html',
  styleUrls: ['./naseljena-mesta.component.css']
})
export class NaseljenaMestaComponent implements OnInit {

  tableInfo: TableInfo = {
    kolone: ['Sifra mesta', 'Naziv', 'PTToznaka', 'Id drzave'],
    atributi: ['id', 'naziv', 'ptt', 'drzavaId'],
    podaci: [],
    naslov: 'Naseljena mesta'
  };

  form: FormGroup;
  drzave = [];

  constructor(private fb: FormBuilder, private http: HttpClient, private dialog: MatDialog) { }

  ngOnInit() {
    this.form = this.fb.group({
      naziv: ['', [Validators.required, Validators.maxLength(60)]],
      ptt: ['', [Validators.required, Validators.maxLength(12)]],
      drzavaId: [0, Validators.required]
    });

    this.http.get<any>(environment.hostUrl + '/api/naseljenaMesta')
      .subscribe(naseljenaMesta => this.tableInfo.podaci = naseljenaMesta);

    this.http.get<any>(environment.hostUrl + '/api/drzave')
      .subscribe(drzave => this.drzave = drzave);
  }

  onSelectDrzava() {
    const dialogRef = this.dialog.open(ZoomTableComponent, {
      data: {
        kolone: ['Sifra drzave', 'Naziv drzave'],
        atributi: ['id', 'nazivDrzave'],
        podaci: this.drzave,
        naslov: 'Drzave'
      }
    });

    dialogRef.afterClosed().subscribe(data => {
        if (data) {
          this.form.patchValue({
            drzavaId: data.id
          });
        }
      }
    );
  }

  onSubmit() {
    if (this.form.valid) {
      this.http.post<any>(environment.hostUrl + '/api/naseljenaMesta', this.form.value)
        .subscribe(naseljenaMesto => this.tableInfo.podaci.push(naseljenaMesto));
    }
  }
}
