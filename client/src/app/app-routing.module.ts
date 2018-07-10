import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DrzaveComponent } from './components/drzave/drzave.component';
import { ValuteComponent } from './components/valute/valute.component';
import { NaseljenaMestaComponent } from './components/naseljena-mesta/naseljena-mesta.component';

const routes: Routes = [
  { path: '', redirectTo: 'drzave', pathMatch: 'full' },
  { path: 'drzave', component: DrzaveComponent },
  { path: 'valute', component: ValuteComponent },
  { path: 'naseljenaMesta', component: NaseljenaMestaComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
