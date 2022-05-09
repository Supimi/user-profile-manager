package com.helthyme.user.service;

import com.helthyme.user.dto.User;
import com.helthyme.user.mapper.UserMapper;
import com.helthyme.user.model.UserData;
import com.helthyme.user.repository.UserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public User saveUserData(UserData userData) {
        try {
            userData.setUserId(UUID.randomUUID().toString());
            this.userDataRepository.save(userData);
            return UserMapper.to(userData);

        } catch (Exception e) {
            log.error("Error occurred while saving the user data", e);
            e.printStackTrace();
        }
        return null;
    }
}
