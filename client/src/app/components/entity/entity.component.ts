import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material';
import { ActivatedRoute } from '@angular/router';

import { TableInfo, FormaView } from '../../models/tableInfo';
import { ZoomTableComponent } from '../zoom-table/zoom-table.component';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrls: ['./entity.component.css']
})
export class EntityComponent implements OnInit {

  highlighted = -1;
  tableInfo: TableInfo;
  podaci: any[];
  form: FormGroup;

  constructor(private fb: FormBuilder,
              private http: HttpClient,
              private route: ActivatedRoute,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.route.data.subscribe(tableInfo => {
      this.tableInfo = <TableInfo> tableInfo;
      this.form = this.fb.group(this.tableInfo.modelForme);

      this.http.get<any>(this.tableInfo.podaciUrl).subscribe(podaci => this.podaci = podaci);
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.http.post<any>(this.tableInfo.podaciUrl, this.form.value)
        .subscribe(drzava => this.podaci.push(drzava));
    }
  }

  onSelectRelation(prikaz: FormaView) {
    const dialogRef = this.dialog.open(ZoomTableComponent, {
      data: prikaz.table
    });

    dialogRef.afterClosed().subscribe(data => {
        if (data) {
          const patchValue = { };
          patchValue[prikaz.imeKontrole] = data[prikaz.roditeljskaKolona];
          this.form.patchValue(patchValue);
        }
      }
    );
  }

  onFirst() {
    if (this.podaci.length > 0) {
      this.highlighted = 0;
    } else {
      this.highlighted = -1;
    }
  }

  onPrevious() {
    if (this.highlighted - 1 >= 0) {
      this.highlighted--;
    } else {
      this.highlighted = this.podaci.length - 1;
    }
  }

  onNext() {
    if (this.highlighted + 1 < this.podaci.length) {
      this.highlighted++;
    } else if (this.podaci.length > 0) {
      this.highlighted = 0;
    }
  }

  onLast() {
    this.highlighted = this.podaci.length - 1;
  }

}
