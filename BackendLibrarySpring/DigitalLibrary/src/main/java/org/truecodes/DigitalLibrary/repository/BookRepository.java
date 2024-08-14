package org.truecodes.DigitalLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.truecodes.DigitalLibrary.model.Book;
import org.truecodes.DigitalLibrary.model.BookType;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByBookType(BookType bookType);
}
