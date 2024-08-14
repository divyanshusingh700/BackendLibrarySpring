package org.truecodes.DigitalLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.truecodes.DigitalLibrary.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    //native query --> simple query which is not provided by hibernate but by sql
    @Query(value="select * from author where email=:email", nativeQuery = true)
    Author getAuthorByEmail(String email);
}
