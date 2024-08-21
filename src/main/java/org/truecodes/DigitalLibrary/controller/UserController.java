package org.truecodes.DigitalLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.truecodes.DigitalLibrary.dto.UserRequest;
import org.truecodes.DigitalLibrary.model.Book;
import org.truecodes.DigitalLibrary.model.User;
import org.truecodes.DigitalLibrary.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addStudent")
    public User addStudent(@RequestBody UserRequest userRequest){
        return userService.addStudent(userRequest);
    }

    @PostMapping("/addAdmin")
    public User addAdmin(@RequestBody UserRequest userRequest){
//        userService.addAdmin(userRequest);
        return null;
    }

    @GetMapping("/filter")
    public List<User> filter(@RequestParam("filterBy") String filterBy,
                                  @RequestParam("operator") String operator,
                                  @RequestParam("value")String values){
        return userService.filter(filterBy, operator, values);

    }

}
