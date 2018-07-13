import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './modules/material.module';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { EntityComponent } from './components/entity/entity.component';
import { TableComponent } from './components/table/table.component';
import { ZoomTableComponent } from './components/zoom-table/zoom-table.component';
import { IzvodKlijentaComponent } from './components/izvod-klijenta/izvod-klijenta.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    EntityComponent,
    TableComponent,
    ZoomTableComponent,
    IzvodKlijentaComponent
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
    ZoomTableComponent,
    IzvodKlijentaComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
