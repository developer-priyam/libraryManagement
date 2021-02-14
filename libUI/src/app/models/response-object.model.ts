import { Book } from './Book.model';
import { User } from './user.model';

export interface ResponseObject {
    user: User;
    book: Book[];
    status: string;
}