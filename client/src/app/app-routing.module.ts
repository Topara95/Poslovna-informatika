import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DrzaveComponent } from './components/drzave/drzave.component';
import { ValuteComponent } from './components/valute/valute.component';

const routes: Routes = [
  { path: '', redirectTo: 'drzave', pathMatch: 'full' },
  { path: 'drzave', component: DrzaveComponent },
  { path: 'valute', component: ValuteComponent }
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
