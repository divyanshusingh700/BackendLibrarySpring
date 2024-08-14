package org.truecodes.DigitalLibrary.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.nio.MappedByteBuffer;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity //class should be connected to database
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(length = 30)// by default 255
    private String title;

    @Column(length = 20,unique = true)// by default 255
    private String bookNo;

    @Enumerated(value = EnumType.STRING)
    private BookType bookType;

    private Integer securityAmount;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Author author;

    @CreationTimestamp //when the row get inserted it gets updated
    private Date createdOn;

    @UpdateTimestamp // when this row gets updated it will notify the time
    private Date updatedOn;

    @OneToMany(mappedBy = "book")
    private List<Txn> txnList;

}
