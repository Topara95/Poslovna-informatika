import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-valute',
  templateUrl: './valute.component.html',
  styleUrls: ['./valute.component.css']
})
export class ValuteComponent implements OnInit {

  form: FormGroup;
  drzave = [];
  valute = [];

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit() {
    this.form = this.fb.group({
      zvanicnaSifra: ['', [Validators.required, Validators.maxLength(3), Validators.minLength(3)]],
      naziv: ['', [Validators.required, Validators.maxLength(30)]],
      domicilna: [false, Validators.required],
      drzavaId: [0, Validators.required]
    });

    this.http.get<any>(environment.hostUrl + '/api/drzave')
      .subscribe(drzave => this.drzave = drzave);

      this.http.get<any>(environment.hostUrl + '/api/valute')
      .subscribe(valute => this.valute = valute);
  }

  onSubmit() {
    if (this.form.valid) {
      this.http.post<any>(environment.hostUrl + '/api/valute', this.form.value)
        .subscribe(valuta => this.valute.push(valuta));
    }
  }

}
