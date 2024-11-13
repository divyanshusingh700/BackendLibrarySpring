package org.truecodes.DigitalLibrary.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.truecodes.DigitalLibrary.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestBookController.class})
public class TestBookController {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testAddBook() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authorName","john");
        jsonObject.put("authorEmail","john@gmail.com");
        jsonObject.put("securityAmount",100);
        jsonObject.put("bookNo","1");
        jsonObject.put("bookTitle","Java intro");
        jsonObject.put("bookType","EDUCATIONAL");

        RequestBuilder requestBuilder = post("/book/addBook")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(jsonObject));

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
