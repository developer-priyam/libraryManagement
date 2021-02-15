# libraryManagement - Docs

# Java - Library Managment Service (Sping book) - 
Java 8

# Angular - Library Managment UI
Angular 11

## Java
### Important Clases
BookController - It is the front controller of the java service
it has 3 endpoints
/books - list of books available in library
/user/{username} - Getting user details, to see how many books are issued
/libManage/{action}/{username}/{bookname} - Called for borrowing and Returning Books 

#### BookService
It has 3 methods

public getBookMap() - reads from in-memory dummy data store and returns map of book data ( Map<String, Book>)

public borrowReturnBook(List<Integer> issuedBooks, String bookname, String action) throws Exception - handles borrow / return requeest

private updateBookMap(List<Integer> issuedBooks, Map<String, Book> bookMap, String bookname, String action) throws Exception - checks for different validations mentioned in the assignment description.

#### UserService
It has 2 methods
public getUserDetails(String username) throws Exception - reads dummy user data and returns the User object

public updateUserDetails(String username, String bookname, String action) - updates user details based on borrow and return request

### POJS's
#### Book - model for book details
#### User - model for user details

### Data store and loader
#### DummyDataLoader - It implements ommandLineRunner and loads dummy data on application start.
#### DummyLibraryDataStore - It has static map type member variable for Book and USer data. This in-memory data is used for all the operations.

## Angular
This has following components
### App component
### Lib - dashboard component - loads on route ''.  Holds Book and user component
### Book detail component - displays the book list and performs issue operation
### User Component - show the books issued to a user and performs book return operation and user load request.

# Test Cases
## Java - By defaultit uses junit5 but in my ide there was some problem, so ran them using junit4 (91+ % coverage)

## Angular - Test cases are written in karma


