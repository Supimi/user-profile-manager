package com.helthyme.user.service;

import com.helthyme.user.Constants;
import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserActivity;
import com.helthyme.user.dto.UserNutrition;
import com.helthyme.user.mapper.UserNutritionMapper;
import com.helthyme.user.model.ActivityData;
import com.helthyme.user.model.NutritionData;
import com.helthyme.user.repository.UserNutritionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserNutritionService
{
    private final UserNutritionRepository userNutritionRepository;

    @Autowired
    protected UserNutritionMapper userNutritionMapper;

    public UserNutritionService(UserNutritionRepository userNutritionRepository ) {
        this.userNutritionRepository = userNutritionRepository;
    }

    public Response<List<UserNutrition>> saveUserTakenFoodItems( List<UserNutrition> userNutrition) {
        boolean success = false;
        List<UserNutrition> updatedUserActivities = null;
        String message = Constants.Response.SUCCESS;

        try {
            List<NutritionData> nutritionDataList = this.userNutritionMapper.userNutritionListToNutritionDataList( userNutrition );
            nutritionDataList.forEach( nutrition -> {
                nutrition.setId( UUID.randomUUID().toString() );
            } );
            this.userNutritionRepository.saveAll( nutritionDataList );
            updatedUserActivities = this.userNutritionMapper.nutritionDataListToUserNutritionList(nutritionDataList);
            success = true;

        } catch (Exception e) {
            log.error("Error occurred while saving the user taken nutritions", e);
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<List<UserNutrition>>builder()
                .isSuccess(success)
                .data(updatedUserActivities)
                .message(message)
                .build();
    }
}
