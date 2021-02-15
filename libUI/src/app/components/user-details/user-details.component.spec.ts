import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { Book } from 'src/app/models/Book.model';
import { User } from 'src/app/models/user.model';
import { LibServiceService } from 'src/app/service/lib-service.service';

import { UserDetailsComponent } from './user-details.component';

describe('UserDetailsComponent', () => {
  let component: UserDetailsComponent;
  let fixture: ComponentFixture<UserDetailsComponent>;
  let service: LibServiceService;
  const bookId = 1;
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
      declarations: [ UserDetailsComponent ],
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
    fixture = TestBed.createComponent(UserDetailsComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(LibServiceService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('load user', () => {
    const serviceSpy = spyOn(LibServiceService.prototype, 'getUser')
    .and.returnValue(of(user));
    component.usernameControl.setValue('test');
    component.loadUserDetails();
    expect(serviceSpy).toHaveBeenCalled();
  });

  it('return book to library', () => {
    const serviceSpy = spyOn(LibServiceService.prototype, 'libManage')
    .and.returnValue(of(user));
    service.storeBookList(bookList);
    component.returnBookToLibrary(bookId);
    expect(serviceSpy).toHaveBeenCalled();
  });
});
