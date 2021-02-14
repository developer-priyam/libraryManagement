import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LibServiceService {
  private serviceBaseURL = 'http://localhost:8080/';
  private getBooksURI = 'books';
  private getUserURI = 'user';
  private libeOperationURI = 'libManage';

  constructor(private http: HttpClient) { }

  getBookList(): Observable<any> {
    return this.http.get(`${this.serviceBaseURL}${this.getBooksURI}`);
  }

  getUser(username: {username: string}): Observable<any> {
    return this.http.post(`${this.serviceBaseURL}${this.getUserURI}`, username);
  }

  libManage(libOps: {username: string, bookname: string, action: string}): Observable<any> {
    return this.http.post(`${this.serviceBaseURL}${this.libeOperationURI}`, libOps);
  }
}
