import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button'
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSnackBarModule} from '@angular/material/snack-bar';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import { LibDashboardComponent } from './components/lib-dashboard/lib-dashboard.component';
import { LibServiceService } from './service/lib-service.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ConvertToNamePipe } from './pipe/convert-to-name.pipe';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailsComponent,
    BookDetailComponent,
    LibDashboardComponent,
    ConvertToNamePipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule
  ],
  providers: [LibServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
