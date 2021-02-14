import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Book } from 'src/app/models/Book.model';
import { ResponseObject } from 'src/app/models/response-object.model';
import { User } from 'src/app/models/user.model';
import { LibServiceService } from 'src/app/service/lib-service.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  bookList: Book[] = [];
  issuedBooks: number[] = [];

  constructor(private service: LibServiceService, private alertBar: MatSnackBar) { 
    this.service.bookListUpdateEvent.subscribe(
      (books: Book[]) => this.bookList = books
    )
    this.service.userUpdateEvent.subscribe(
      (user: User) => this.issuedBooks = user.issuedBooks 
    )
  }

  ngOnInit(): void {
    this.service.getBookList()
    .subscribe(
      (response: Book[]) => {
        this.bookList = response
        this.service.storeBookList(this.bookList);
        this.showMesage('Book list fecthed');
      }
    )
  }

  issueBookFromLibrary(bookname: string): void {
    const username = this.service.getUserName();
    if(username !== '') {
      const reqObj = {
        username: this.service.getUserName(),
        bookname,
        action: 'borrow'
      }
      this.service.libManage(reqObj)
      .subscribe(
        (response: ResponseObject) => {
          this.service.storeBookList(response.book);
          this.issuedBooks = response.user.issuedBooks;
          this.service.userUpdateEvent.emit(response.user);
          this.showMesage(` Issue ${response.status}`);
        }
      )
    } else {
      this.showMesage('Please load the user details first');
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
