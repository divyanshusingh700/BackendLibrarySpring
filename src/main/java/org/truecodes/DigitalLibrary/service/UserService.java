package org.truecodes.DigitalLibrary.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.truecodes.DigitalLibrary.dto.UserRequest;
import org.truecodes.DigitalLibrary.exception.UserException;
import org.truecodes.DigitalLibrary.model.*;
import org.truecodes.DigitalLibrary.repository.UserCacheRepository;
import org.truecodes.DigitalLibrary.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private static final Log logger = LogFactory.getLog(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${student.authority}")
    private String studentAuthority;

    @Value("${admin.authority}")
    private String adminAuthority;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserCacheRepository userCacheRepository;
    public User addStudent(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setAuthorities(studentAuthority);
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setUserType(UserType.STUDENT);
        return userRepository.save(user);
    }

    public List<User> filter(String filterBy, String operator, String value) {
        String[] filters = filterBy.split(",");
        String[] operators = operator.split(",");
        String[] values = value.split(",");
        StringBuilder query = new StringBuilder();
        query.append("select * from user where ");
        for(int i=0;i<operators.length;i++){
            UserFilterType userFilterType = UserFilterType.valueOf(filters[i]);
            Operator operator1 = Operator.valueOf(operators[i]);
            String finalValue = values[i];

            query = query
                    .append(userFilterType)
                    .append(operator1.getValue())
                    .append("'")
                    .append(finalValue)
                    .append("'").append(" and ");
//            switch (userFilterType){
//                case NAME:
//                    return
//                case EMAIL:
//            }
        }

        logger.info("query is : "+ query.substring(0,query.length()-4));
        Query query1 = em.createNativeQuery(query.substring(0,query.length()-4), User.class);
        return query1.getResultList();
//        return userRepository.findUsersByNativeQuery(query.substring(0,query.length()-4));

    }

    public User getStudentByPhoneNo(String userPhoneNo) {
        return userRepository.findByPhoneNoAndUserType(userPhoneNo, UserType.STUDENT);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userCacheRepository.getUser(email);
        if(user!=null){
            return user;
        }

        user = userRepository.findByEmail(email);
        if(user == null){
            new UserException("user does not belong to library");
        }
        userCacheRepository.setUser(email,user);
        return user;
    }

    public User addAdmin(UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setAuthorities(adminAuthority);
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setUserType(UserType.ADMIN);
        return userRepository.save(user);
    }
}
