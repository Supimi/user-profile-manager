package com.helthyme.user.service;

import com.helthyme.user.Constants;
import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserActivity;
import com.helthyme.user.mapper.UserActivityMapper;
import com.helthyme.user.model.ActivityData;
import com.helthyme.user.repository.UserActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserActivityService
{
    private final UserActivityRepository userActivityRepository;

    @Autowired
    protected UserActivityMapper userActivityMapper;

    public UserActivityService(UserActivityRepository userActivityRepository ) {
        this.userActivityRepository = userActivityRepository;
    }

    public Response<List<UserActivity>> saveUserActivities( List<UserActivity> userActivity) {
        boolean success = false;
        List<UserActivity> updatedUserActivities = null;
        String message = Constants.Response.SUCCESS;

        try {
            List<ActivityData> activityDataList = this.userActivityMapper.userActivityListToActivityDataList( userActivity );
            activityDataList.forEach( activityData -> {
                activityData.setId( UUID.randomUUID().toString() );
            } );
            this.userActivityRepository.saveAll( activityDataList );
            updatedUserActivities = this.userActivityMapper.activityDataListToUserActivityList(activityDataList);
            success = true;

        } catch (Exception e) {
            log.error("Error occurred while saving the user activities", e);
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<List<UserActivity>>builder()
                .isSuccess(success)
                .data(updatedUserActivities)
                .message(message)
                .build();
    }

}
