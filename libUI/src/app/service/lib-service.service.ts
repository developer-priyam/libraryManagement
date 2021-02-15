import { HttpClient } from '@angular/common/http';
import { Injectable, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../models/Book.model';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LibServiceService {
  private serviceBaseURL = 'http://localhost:8080/';
  private getBooksURI = 'books';
  private getUserURI = 'user';
  private libeOperationURI = 'libManage';
  private username = '';
  private bookList: Book[] = [];
  public userUpdateEvent = new EventEmitter<User>();
  public bookListUpdateEvent = new EventEmitter<Book[]>();

  constructor(private http: HttpClient) { }

  getBookList(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.serviceBaseURL}${this.getBooksURI}`);
  }

  getUser(username: string): Observable<User> {
    return this.http.post<User>(`${this.serviceBaseURL}${this.getUserURI}/${username}`, {});
  }

  libManage(action: string, username: string, bookname: string): Observable<User> {
    return this.http.post<User>(`${this.serviceBaseURL}${this.libeOperationURI}/${action}/${username}/${bookname}`, {});
  }

  storeUserName(username: string): void {
    this.username = username;
  }

  getUserName(): string {
    return this.username;
  }

  storeBookList(bookList: Book[]): void {
    this.bookList = bookList;
    this.bookListUpdateEvent.emit(this.bookList);
  }

  getStroedBookList(): Book[] {
    return this.bookList;
  }
}
