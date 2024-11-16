package org.truecodes.DigitalLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.truecodes.DigitalLibrary.model.User;
import org.truecodes.DigitalLibrary.model.UserType;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where :query",nativeQuery = true)
    List<User> findUsersByNativeQuery(@Param("query") String q);

    User findByPhoneNoAndUserType(String phoneNo, UserType type);

    User findByEmail(String email);
}
