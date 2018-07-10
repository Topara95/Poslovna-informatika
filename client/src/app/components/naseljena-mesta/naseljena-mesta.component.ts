import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-naseljena-mesta',
  templateUrl: './naseljena-mesta.component.html',
  styleUrls: ['./naseljena-mesta.component.css']
})
export class NaseljenaMestaComponent implements OnInit {

  form: FormGroup;
  naseljenaMesta = [];
  drzave = [];

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit() {
    this.form = this.fb.group({
      naziv: ['', [Validators.required, Validators.maxLength(60)]],
      ptt: ['', [Validators.required, Validators.maxLength(12)]],
      drzavaId: [0, Validators.required]
    });

    this.http.get<any>(environment.hostUrl + '/api/naseljenaMesta')
      .subscribe(naseljenaMesta => this.naseljenaMesta = naseljenaMesta);

    this.http.get<any>(environment.hostUrl + '/api/drzave')
      .subscribe(drzave => this.drzave = drzave);
  }

  onSubmit() {
    if (this.form.valid) {
      this.http.post<any>(environment.hostUrl + '/api/naseljenaMesta', this.form.value)
        .subscribe(naseljenaMesto => this.naseljenaMesta.push(naseljenaMesto));
    }
  }
}
