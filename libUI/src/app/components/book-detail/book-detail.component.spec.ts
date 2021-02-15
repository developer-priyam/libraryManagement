import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LibServiceService } from 'src/app/service/lib-service.service';
import { BookDetailComponent } from './book-detail.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { Book } from 'src/app/models/Book.model';
import { Observable, of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { User } from 'src/app/models/user.model';

describe('BookDetailComponent', () => {
  let component: BookDetailComponent;
  let fixture: ComponentFixture<BookDetailComponent>;
  let service: LibServiceService;
  const bookList: Book[] = [
    {
      id: 1,
      name: 'test-book',
      availableCopies: 5,
      totalCopies: 5
    }
  ];

  const user: User = {
    id: 1,
    name: 'test',
    issuedBooks: [1]
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookDetailComponent ],
      providers: [{provide: service, useClass: LibServiceService}],
      imports: [
        HttpClientTestingModule,
        MatSnackBarModule,
        BrowserAnimationsModule,
        MatButtonModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookDetailComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(LibServiceService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get List of books in the Library', () => {
    const serviceSpy = spyOn(LibServiceService.prototype, 'getBookList')
                       .and.returnValue(of(bookList));
    component.getBooks();
    expect(serviceSpy).toHaveBeenCalled();
    expect(component.bookList.length).toEqual(1);
  });

  it('should issue the book', () => {
    const serviceSpy = spyOn(LibServiceService.prototype, 'libManage')
                        .and.returnValue(of(user));
    spyOn(LibServiceService.prototype, 'getUserName')
    .and.returnValue("test");
    const bookname = 'test-book';
    component.bookList = bookList;
    component.issueBookFromLibrary(bookname);
    expect(serviceSpy).toHaveBeenCalled();
  });
});
