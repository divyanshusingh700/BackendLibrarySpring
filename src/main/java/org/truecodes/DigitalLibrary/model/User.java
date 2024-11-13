package org.truecodes.DigitalLibrary.model;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)// by default 255
    private String name;

    @Column(nullable = false, unique = true,length = 15)
    private String phoneNo;

    @Column(length = 50,nullable=false, unique = true)
    private String email;

    @CreationTimestamp //when the row get inserted it gets updated
    private Date createdOn;

    @UpdateTimestamp // when this row gets updated it will notify the time
    private Date updatedOn;
    @Enumerated
    private UserStatus userStatus;

    @Enumerated(value = EnumType.STRING) // whenever we are dealing with enums we have to give this annotation, by default this is ORDINAL(index 0 starts)
    private UserType userType;
    private String address;

    @OneToMany(mappedBy="user")
    private List<Book> bookList;

    @OneToMany(mappedBy="user")
    @JsonIgnoreProperties(value = {"user","book"})
    private List<Txn> txnList;

}
