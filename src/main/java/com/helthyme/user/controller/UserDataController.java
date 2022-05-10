package com.helthyme.user.controller;

import com.helthyme.user.dto.User;
import com.helthyme.user.domain.Response;
import com.helthyme.user.model.UserData;
import com.helthyme.user.service.UserDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserDataController {

    private final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping
    public Response<User> healthCheck(@RequestBody UserData user){
       return this.userDataService.saveUserData(user);
    }
}
