import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { HttpClient } from '../../../../node_modules/@angular/common/http';

import { environment } from '../../../environments/environment';
import { TableInfo } from '../../models/tableInfo';

@Component({
  selector: 'app-drzave',
  templateUrl: './drzave.component.html',
  styleUrls: ['./drzave.component.css']
})
export class DrzaveComponent implements OnInit {

  tableInfo: TableInfo = {
    kolone: ['Sifra drzave', 'Naziv drzave'],
    atributi: ['id', 'nazivDrzave'],
    podaci: [],
    naslov: 'Drzave'
  };

  form: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit() {
    this.form = this.fb.group({
      id: [0, Validators.required],
      nazivDrzave: ['', Validators.required]
    });

    this.http.get<any>(environment.hostUrl + '/api/drzave')
      .subscribe(drzave => this.tableInfo.podaci = drzave);
  }

  onSubmit() {
    if (this.form.valid) {
      this.http.post<any>(environment.hostUrl + '/api/drzave', this.form.value)
        .subscribe(drzava => this.tableInfo.podaci.push(drzava));
    }
  }

}
