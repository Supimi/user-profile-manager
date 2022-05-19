package com.helthyme.user.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.helthyme.user.Constants;
import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserActivity;
import com.helthyme.user.dto.UserMeal;
import com.helthyme.user.mapper.UserMealMapper;
import com.helthyme.user.model.ActivityData;
import com.helthyme.user.model.MealData;
import com.helthyme.user.repository.UserMealRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserMealService
{
    private final UserMealRepository userMealRepository;

    @Autowired
    protected UserMealMapper userMealMapper;
    @Autowired
    DynamoDBMapper dynamoDBMapper;

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

    public Response<List<UserMeal>> getUserMealByUserIdAndDate( String userId )
    {
        boolean success = false;
        List<UserMeal> retrieveUserMeals = null;
        String message = Constants.Response.SUCCESS;
        try
        {
            Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
            eav.put( ":v1", new AttributeValue().withS( userId ) );


            DynamoDBQueryExpression<MealData> queryExpression = new DynamoDBQueryExpression<MealData>()
                    .withIndexName( "user_id-index" ).withConsistentRead(false)
                    .withKeyConditionExpression( "user_id = :v1" )
                    .withExpressionAttributeValues( eav );
            List<MealData> mealData = dynamoDBMapper.query( MealData.class, queryExpression );
            retrieveUserMeals = this.userMealMapper.mealDataListToUserMealList( mealData );
            success = true;

        }
        catch( Exception e )
        {
            log.error( "Error occurred while get the user activities", e );
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<List<UserMeal>>builder()
                .isSuccess( success )
                .data( retrieveUserMeals )
                .message( message )
                .build();
    }
}
