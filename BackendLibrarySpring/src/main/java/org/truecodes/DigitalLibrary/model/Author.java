package org.truecodes.DigitalLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity //class should be connected to database
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(length = 30)// by default 255
    private String name;

    @Column(length = 50,unique = true)
    private String email;

    @OneToMany(mappedBy="author")
//    @JsonIgnore
    @JsonIgnoreProperties(value = "author")
    private List<Book> bookList;

    @CreationTimestamp //when the row get inserted it gets updated
    private Date createdOn;

    @UpdateTimestamp // when this row gets updated it will notify the time
    private Date updatedOn;

}
