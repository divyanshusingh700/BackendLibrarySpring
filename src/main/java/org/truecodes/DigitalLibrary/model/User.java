package org.truecodes.DigitalLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity //class should be connected to database
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)// by default 255
    private String name;

    private String password;
    private String authorities;

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

    @JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<Book> bookList;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"user","book"})
    private List<Txn> txnList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] auth = authorities.split(",");
        return Arrays.stream(auth).map(a->new SimpleGrantedAuthority(a)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
