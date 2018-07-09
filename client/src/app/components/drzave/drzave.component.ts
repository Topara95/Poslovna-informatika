import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { HttpClient } from '../../../../node_modules/@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-drzave',
  templateUrl: './drzave.component.html',
  styleUrls: ['./drzave.component.css']
})
export class DrzaveComponent implements OnInit {

  form: FormGroup;
  drzave = [];

  constructor(private fb: FormBuilder, private http: HttpClient) { }

  ngOnInit() {
    this.form = this.fb.group({
      id: [0, Validators.required],
      nazivDrzave: ['', Validators.required]
    });

    this.http.get<any>(environment.hostUrl + '/api/drzave')
      .subscribe(drzave => this.drzave = drzave);
  }

  onSubmit() {
    if (this.form.valid) {
      this.http.post<any>(environment.hostUrl + '/api/drzave', this.form.value)
        .subscribe(drzava => this.drzave.push(drzava));
    }
  }

}
