import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibDashboardComponent } from './components/lib-dashboard/lib-dashboard.component';

const routes: Routes = [
  {
    path: '', component: LibDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
