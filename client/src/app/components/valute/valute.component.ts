import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material';

import { environment } from '../../../environments/environment';
import { TableInfo } from '../../models/tableInfo';
import { ZoomTableComponent } from '../zoom-table/zoom-table.component';

@Component({
  selector: 'app-valute',
  templateUrl: './valute.component.html',
  styleUrls: ['./valute.component.css']
})
export class ValuteComponent implements OnInit {

  tableInfo: TableInfo = {
    kolone: ['Id valute', 'Zvanicna sifra', 'Naziv', 'Domicilna', 'Drzava'],
    atributi: ['id', 'zvanicnaSifra', 'naziv', 'domicilna', 'drzavaId'],
    podaci: [],
    naslov: 'Valute'
  };

  form: FormGroup;
  drzave = [];

  constructor(private fb: FormBuilder, private http: HttpClient, private dialog: MatDialog) { }

  ngOnInit() {
    this.form = this.fb.group({
      zvanicnaSifra: ['', [Validators.required, Validators.maxLength(3), Validators.minLength(3)]],
      naziv: ['', [Validators.required, Validators.maxLength(30)]],
      domicilna: [false, Validators.required],
      drzavaId: [null, Validators.required]
    });

    this.http.get<any>(environment.hostUrl + '/api/drzave')
      .subscribe(drzave => this.drzave = drzave);

    this.http.get<any>(environment.hostUrl + '/api/valute')
      .subscribe(valute => this.tableInfo.podaci = valute);
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
      this.http.post<any>(environment.hostUrl + '/api/valute', this.form.value)
        .subscribe(valuta => this.tableInfo.podaci.push(valuta));
    }
  }

}
