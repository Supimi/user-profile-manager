package com.helthyme.user.mapper;

import com.helthyme.user.dto.User;
import com.helthyme.user.model.UserData;


public class UserMapper {

    public static User to(UserData userData){
        return User.builder()
                .userName(userData.getUserName())
                .weight(userData.getWeight())
                .height(userData.getHeight())
                .build();
    }
}
