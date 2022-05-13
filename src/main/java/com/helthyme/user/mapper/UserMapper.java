package com.helthyme.user.mapper;

import com.helthyme.user.dto.User;
import com.helthyme.user.model.UserData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User userDataToUser(UserData userData);

    public abstract UserData userToUserData(User user);
}
