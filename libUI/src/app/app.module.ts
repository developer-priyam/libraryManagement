import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import { LibDashboardComponent } from './components/lib-dashboard/lib-dashboard.component';
import { LibServiceService } from './service/lib-service.service';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailsComponent,
    BookDetailComponent,
    LibDashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [LibServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
