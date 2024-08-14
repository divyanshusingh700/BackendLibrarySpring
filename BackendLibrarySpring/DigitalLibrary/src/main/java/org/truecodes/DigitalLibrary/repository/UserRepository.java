package org.truecodes.DigitalLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.truecodes.DigitalLibrary.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where :q",nativeQuery = true)
    List<User> findUsersByNativeQuery(String q);
}
