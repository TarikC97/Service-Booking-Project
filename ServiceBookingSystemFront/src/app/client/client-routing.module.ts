import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client.component';
import { ClientDashboardComponent } from './pages/client-dashboard/client-dashboard.component';
import { AdDetailComponent } from './pages/ad-detail/ad-detail.component';

const routes: Routes = [
  { path: '', component: ClientComponent },
  { path: 'dashboard', component: ClientDashboardComponent },
  { path: 'ad/:adId', component: AdDetailComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
