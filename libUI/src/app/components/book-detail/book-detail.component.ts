import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/Book.model';
import { ResponseObject } from 'src/app/models/response-object.model';
import { LibServiceService } from 'src/app/service/lib-service.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  bookList: Book[] = [];
  issuedBooks: number[] = [];

  constructor(private service: LibServiceService) { 
    this.service.bookListUpdateEvent.subscribe(
      (books: Book[]) => this.bookList = books
    )
  }

  ngOnInit(): void {
    this.service.getBookList()
    .subscribe(
      (response: Book[]) => {
        this.bookList = response
        this.service.storeBookList(this.bookList);
      }
    )
  }

  issueBookFromLibrary(bookname: string): void {
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
        console.log(response.status);
      }
    )
  }

}
