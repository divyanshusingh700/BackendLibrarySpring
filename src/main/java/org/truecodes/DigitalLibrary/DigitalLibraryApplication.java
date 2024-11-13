package org.truecodes.DigitalLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.truecodes.DigitalLibrary.repository.BookRepository;
import org.truecodes.DigitalLibrary.repository.TxnRepository;

@SpringBootApplication
public class DigitalLibraryApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TxnRepository txnRepository;

	public static void main(String[] args) {
		SpringApplication.run(DigitalLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		txnRepository.updateExistingTxn();
		txnRepository.updateBook();
		System.out.println("My application has been started !!");
	}
}
