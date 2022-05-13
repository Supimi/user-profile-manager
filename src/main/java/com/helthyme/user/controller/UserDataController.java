package com.helthyme.user.controller;

import com.helthyme.user.dto.User;
import com.helthyme.user.domain.Response;
import com.helthyme.user.service.UserDataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserDataController {

    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping
    public Response<User> saveUser(@RequestBody User user){
       return this.userDataService.saveUserData(user);
    }

    @GetMapping("{id}")
    public Response<User> getUser(@PathVariable String id){
        return this.userDataService.getUserData(id);
    }
}
