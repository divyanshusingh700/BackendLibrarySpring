package org.truecodes.DigitalLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truecodes.DigitalLibrary.dto.BookRequest;
import org.truecodes.DigitalLibrary.model.*;
import org.truecodes.DigitalLibrary.repository.AuthorRepository;
import org.truecodes.DigitalLibrary.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    public Book addBook(BookRequest bookRequest) {
        Author authorFromDB = authorRepository.getAuthorByEmail(bookRequest.getAuthorEmail());

        if(authorFromDB == null){
            //object of author table
            //save the data in author table?
            authorFromDB = authorRepository.save(bookRequest.toAuthor());
        }
        Book book = bookRequest.toBook();
        book.setAuthor(authorFromDB);
        return bookRepository.save(book);

    }

    public List<Book> filter(BookFilterType filterType,
                             Operator operator,
                             String value)
    {
        switch (filterType){
            case BOOK_TITLE :
                switch (operator){
                    case EQUALS :
                        return bookRepository.findByTitle(value);
                    case LIKE:
                        return bookRepository.findByTitleContaining(value);
                    default:
                        return new ArrayList<>();
                }
            case BOOK_TYPE:
                switch (operator){
                    case EQUALS :
                        return bookRepository.findByBookType(BookType.valueOf(value));
                }
            default:
                new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
