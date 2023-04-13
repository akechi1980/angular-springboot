import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HttpClient } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './pages/user-list/user-list.component';
import { CompanyListComponent } from './pages/company-list/company-list.component';
import { InfoComponent } from './pages/info/info.component';
import { UsersService } from './pages/user-list/users.service';
import { InfoService } from './pages/info/info.service';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    CompanyListComponent,
    InfoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    InfoService,
    UsersService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
