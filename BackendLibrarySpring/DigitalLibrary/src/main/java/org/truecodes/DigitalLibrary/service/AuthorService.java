package org.truecodes.DigitalLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truecodes.DigitalLibrary.model.Author;
import org.truecodes.DigitalLibrary.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthorData(String email){
        return authorRepository.getAuthorByEmail(email);
    }
}
