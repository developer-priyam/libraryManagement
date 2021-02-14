import { Pipe, PipeTransform } from '@angular/core';
import { Book } from '../models/Book.model';
import { LibServiceService } from '../service/lib-service.service';

@Pipe({
  name: 'convertToName'
})
export class ConvertToNamePipe implements PipeTransform {

  constructor(private service: LibServiceService) {}

  transform(value: number): string | undefined {
    const bookname = this.service.getStroedBookList().find((book: Book) => book.id == value)?.name;
    return bookname;
  }

}
