import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './pages/user-list/user-list.component';
import { CompanyListComponent } from './pages/company-list/company-list.component';
import { InfoComponent } from './pages/info/info.component';


const routes: Routes = [
    { path: 'user', component: UserListComponent },
    { path: 'company', component: CompanyListComponent },
    { path: 'info', component: InfoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
