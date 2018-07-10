import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TableRoutes } from './models/data';
import { EntityComponent } from './components/entity/entity.component';

const routes: Routes = [
  { path: '', redirectTo: 'drzave', pathMatch: 'full' }
];

for (const tableRoute of TableRoutes) {
  routes.push({ path: tableRoute.ruta, component: EntityComponent, data: tableRoute.data });
}

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
