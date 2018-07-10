import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './modules/material.module';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DrzaveComponent } from './components/drzave/drzave.component';
import { ValuteComponent } from './components/valute/valute.component';
import { NaseljenaMestaComponent } from './components/naseljena-mesta/naseljena-mesta.component';
import { TableComponent } from './components/table/table.component';
import { ZoomTableComponent } from './components/zoom-table/zoom-table.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DrzaveComponent,
    ValuteComponent,
    NaseljenaMestaComponent,
    TableComponent,
    ZoomTableComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  entryComponents: [
    ZoomTableComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
