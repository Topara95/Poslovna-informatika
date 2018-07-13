import { Component, OnInit, Input, Inject } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { environment } from '../../../environments/environment';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-izvod-klijenta',
  templateUrl: './izvod-klijenta.component.html',
  styleUrls: ['./izvod-klijenta.component.css']
})
export class IzvodKlijentaComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) private id: number) { }

  ngOnInit() {
    this.form = this.fb.group({
      od: [(new Date()).toISOString(), Validators.required],
      do: [(new Date()).toISOString(), Validators.required]
    });
  }

  getLinkZaPdf() {
    const odM = new Date(this.form.value.od);
    const doM = new Date(this.form.value.do);
    return `${environment.hostUrl}/api/lica/fizickalica/${this.id}?from=${this.datum(odM)}&to=${this.datum(doM)}`;
  }

  datum(d: Date): string {
    const mesec = '' + (d.getMonth() + 1);
    const dan = '' + d.getDate();
    const godina = d.getFullYear();

    return `${mesec}/${dan}/${godina}`;
  }

}
