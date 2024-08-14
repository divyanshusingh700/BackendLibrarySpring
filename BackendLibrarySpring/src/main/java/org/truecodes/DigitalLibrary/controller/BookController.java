package org.truecodes.DigitalLibrary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.truecodes.DigitalLibrary.dto.BookRequest;
import org.truecodes.DigitalLibrary.model.Book;
import org.truecodes.DigitalLibrary.model.BookFilterType;
import org.truecodes.DigitalLibrary.model.Operator;
import org.truecodes.DigitalLibrary.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public Book addBook(@RequestBody @Valid BookRequest bookRequest){ // this at valid will call validation checks for book request data
        //Validation before the business logic

        // call the business logic
        Book book = bookService.addBook(bookRequest);

        // return the accurate/required data
        return book;
    }
    @GetMapping("/filter")
    public List<Book> findByTitle(@RequestParam("filterBy") BookFilterType filterType,
                                  @RequestParam("operator")Operator operator,
                                  @RequestParam("value")String value){
        return bookService.filter(filterType, operator, value);

    }

}
// Creating a row in book table
//u have to insert the information related to author who have written this


//Kathy --> intr to java
//kathy --> advance java

// so we do not create 2 row for same author, instead we just create a row in book table