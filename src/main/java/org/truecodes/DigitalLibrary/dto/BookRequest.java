package org.truecodes.DigitalLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.truecodes.DigitalLibrary.model.Author;
import org.truecodes.DigitalLibrary.model.Book;
import org.truecodes.DigitalLibrary.model.BookType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookRequest {
    @NotBlank(message = "book title should not be blank")
    private String bookTitle;
    @NotBlank(message = "book number should not be blank")
    private String bookNo;

    @NotBlank(message = "author Name should not be blank")
    private String authorName;

    @NotBlank(message = "author Email should not be blank")
    private String authorEmail;

    @NotNull(message = "type should not be null")
    private BookType type;

    @Positive(message = "security Amount should be Positive")
    private int securityAmount;

    public Author toAuthor(){
        return Author
                .builder()
                .email(this.authorEmail)
                .name(this.authorName)
                .build();
    }


    public Book toBook() {
        return Book
                .builder()
                .bookNo(this.bookNo)
                .title(this.bookTitle)
                .securityAmount(this.securityAmount)
                .bookType(this.type)
                .build();
    }
}
