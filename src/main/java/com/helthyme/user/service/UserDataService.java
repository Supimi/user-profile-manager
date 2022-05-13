package com.helthyme.user.service;

import com.helthyme.user.Constants;
import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.User;
import com.helthyme.user.mapper.UserMapper;
import com.helthyme.user.model.UserData;
import com.helthyme.user.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.UUID;

@Slf4j
@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;
    @Autowired
    protected UserMapper userMapper;

    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public Response<User> saveUserData(User user) {
        boolean success = false;
        User updatedUser = null;
        String message = Constants.Response.SUCCESS;

        try {
            UserData userData = this.userMapper.userToUserData(user);
            userData.setUserId(UUID.randomUUID().toString());
            this.userDataRepository.save(userData);
            updatedUser = this.userMapper.userDataToUser(userData);
            success = true;

        } catch (Exception e) {
            log.error("Error occurred while saving the user data", e);
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<User>builder()
                .isSuccess(success)
                .data(updatedUser)
                .message(message)
                .build();
    }

    public Response<User> getUserData(String id) {
        boolean success = false;
        User user = null;
        String message = Constants.Response.SUCCESS;
        try {
            UserData userData = this.userDataRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found"));
            user = this.userMapper.userDataToUser(userData);
            success = true;

        } catch (Exception e) {
            log.error("Error occurred while retrieving user:{}", id, e);
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<User>builder()
                .isSuccess(success)
                .data(user)
                .message(message)
                .build();
    }
}
