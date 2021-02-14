import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Book } from 'src/app/models/Book.model';
import { ResponseObject } from 'src/app/models/response-object.model';
import { User } from 'src/app/models/user.model';
import { LibServiceService } from 'src/app/service/lib-service.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  user: User | undefined;
  issuedBooks: number[] = [];
  userLoaded = false;
  usernameControl = new FormControl(null, Validators.required);

  constructor(private service: LibServiceService, private alertBar: MatSnackBar) { 
    this.service.userUpdateEvent.subscribe(
      (user: User) => this.issuedBooks = user.issuedBooks
    )
  }

  ngOnInit(): void { }

  loadUserDetails(): void {
    if(this.usernameControl.value !== null) {
      const obj = {
        username: this.usernameControl.value
      }
      this.service.getUser(obj)
      .subscribe(
        (response: User) => {
          if(response.id > -1) {
            this.issuedBooks = response.issuedBooks;
            this.userLoaded = true;
            this.service.storeUserName(response.name);
            this.showMesage('user Loaded successfully!!');
          }
        }
      )
    } else {
      this.showMesage('Enter the username first');
    }
  }
  
  returnBookToLibrary(bookId: number): void {
    const bookname = this.service.getStroedBookList().find((book: Book) => book.id === bookId)?.name;
    if(bookname !== undefined) {
      const reqObj = {
        username: this.service.getUserName(),
        bookname,
        action: 'return'
      }
      this.service.libManage(reqObj)
      .subscribe(
        (response: ResponseObject) => {
          this.service.storeBookList(response.book);
          this.issuedBooks = response.user.issuedBooks;
          this.service.userUpdateEvent.emit(response.user);
          this.showMesage('Book Return Successful, response status : ' + response.status);
        }
      )
    }
    
  }

  showMesage(message: string): void {
    this.alertBar.open(message, 'X',
      {
        duration: 2000,
        horizontalPosition: 'end',
        verticalPosition: 'top',
      }
    );
  }

}
