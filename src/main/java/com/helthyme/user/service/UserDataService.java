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

    public Response<User> saveUserData(UserData userData) {
        boolean success = false;
        User user = null;
        String message = Constants.Response.SUCCESS;

        try {
            userData.setUserId(UUID.randomUUID().toString());
            this.userDataRepository.save(userData);
            user = userMapper.userDataToUser(userData);
            success = true;

        } catch (Exception e) {
            log.error("Error occurred while saving the user data", e);
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<User>builder()
                .isSuccess(success)
                .data(user)
                .message(message)
                .build();
    }
}
