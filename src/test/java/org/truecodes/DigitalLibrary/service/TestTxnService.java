package org.truecodes.DigitalLibrary.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.truecodes.DigitalLibrary.dto.TxnRequest;
import org.truecodes.DigitalLibrary.exception.TxnException;
import org.truecodes.DigitalLibrary.model.Book;
import org.truecodes.DigitalLibrary.model.Txn;
import org.truecodes.DigitalLibrary.model.User;
import org.truecodes.DigitalLibrary.repository.TxnRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestTxnService.class})
public class TestTxnService {
    @InjectMocks
    private TxnService txnService;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Mock
    private TxnRepository txnRepository;


    @Before
    public void setUp(){
        txnService = new TxnService();
        ReflectionTestUtils.setField(txnService, "validDays", "12");
        ReflectionTestUtils.setField(txnService, "finePerDay", "2");
        MockitoAnnotations.initMocks(this);

    }

//    @Test
//    public void testCalculateFine() throws ParseException {
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2024-05-01");
//        Txn txn = Txn.builder()
//                .createdOn(date)
//                .build();
//        int calculatedAmount = txnService.calculateFine(txn, 100);
//        Assert.assertEquals(60, calculatedAmount);
//    }

    @Test(expected = TxnException.class)
    public void testGetUserFromDb() throws TxnException {
        TxnRequest txnRequest = TxnRequest.builder()
                .build();
        when(userService.getStudentByPhoneNo(any())).thenReturn(null);
        txnService.getUserFromDb(txnRequest);
    }

    @Test(expected = TxnException.class)
    public void testGetUserFromDbWhenNoException() throws TxnException {
        TxnRequest txnRequest = TxnRequest.builder().build();
        User user = User.builder().id(1).build();
        when(userService.getStudentByPhoneNo(any())).thenReturn(user);
        User output = txnService.getUserFromDb(txnRequest);

        Assert.assertEquals(user.getId(),output.getId());
    }

    @Test
    public void testReturnBook() throws TxnException, ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2024-05-01");

        TxnRequest txnRequest = TxnRequest.builder().build();
        User user = User.builder().id(1).build();
        when(userService.getStudentByPhoneNo(any())).thenReturn(user);

        Book book = Book.builder().id(1).bookNo("1").securityAmount(100).build();
        List<Book> list = new ArrayList<>();
        list.add(book);
        when(bookService.filter(any(),any(),any())).thenReturn(list);

        Txn txn = Txn.builder().id(1).user(user).book(book).createdOn(date).build();
        when(txnRepository.findByUserPhoneNoAndBookBookNoAndTxnStatus(any(),any(),any())).thenReturn(txn);
        int fine = txnService.returnBook(txnRequest);
        Assert.assertEquals(0, fine);
    }
}
