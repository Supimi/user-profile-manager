package com.helthyme.user.service;

import com.helthyme.user.Constants;
import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserMeal;
import com.helthyme.user.mapper.UserMealMapper;
import com.helthyme.user.model.MealData;
import com.helthyme.user.repository.UserMealRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserMealService
{
    private final UserMealRepository userMealRepository;

    @Autowired
    protected UserMealMapper userMealMapper;

    public UserMealService( UserMealRepository userMealRepository ) {
        this.userMealRepository = userMealRepository;
    }

    public Response<List<UserMeal>> saveUserTakenFoodItems( List<UserMeal> userMeal ) {
        boolean success = false;
        List<UserMeal> updatedUserActivities = null;
        String message = Constants.Response.SUCCESS;

        try {
            List<MealData> mealDataList = this.userMealMapper.userMealListToMealDataList( userMeal );
            mealDataList.forEach( nutrition -> {
                nutrition.setId( UUID.randomUUID().toString() );
            } );
            this.userMealRepository.saveAll( mealDataList );
            updatedUserActivities = this.userMealMapper.mealDataListToUserMealList( mealDataList );
            success = true;

        } catch (Exception e) {
            log.error("Error occurred while saving the user taken nutritions", e);
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<List<UserMeal>>builder()
                .isSuccess(success)
                .data(updatedUserActivities)
                .message(message)
                .build();
    }
}
