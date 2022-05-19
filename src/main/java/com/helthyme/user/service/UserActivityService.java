package com.helthyme.user.service;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.helthyme.user.Constants;
import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserActivity;
import com.helthyme.user.mapper.UserActivityMapper;
import com.helthyme.user.model.ActivityData;
import com.helthyme.user.repository.UserActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserActivityService
{
    private final UserActivityRepository userActivityRepository;

    @Autowired
    protected UserActivityMapper userActivityMapper;
    @Autowired
    DynamoDBMapper dynamoDBMapper;

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

    public Response<List<UserActivity>> getUserActivitiesByUserIdAndDate( String userId )
    {
        boolean success = false;
        List<UserActivity> retrieveUserActivities = null;
        String message = Constants.Response.SUCCESS;
        try
        {
            Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
            eav.put( ":v1", new AttributeValue().withS( userId ) );

            DynamoDBQueryExpression<ActivityData> queryExpression = new DynamoDBQueryExpression<ActivityData>()
                    .withIndexName( "user_id-index" ).withConsistentRead(false)
                    .withKeyConditionExpression( "user_id = :v1" )
                    .withExpressionAttributeValues( eav );
            List<ActivityData> activityDataList = dynamoDBMapper.query( ActivityData.class, queryExpression );
            retrieveUserActivities = this.userActivityMapper.activityDataListToUserActivityList( activityDataList );
            success = true;

        }
        catch( Exception e )
        {
            log.error( "Error occurred while get the user activities", e );
            message = Constants.Response.FAILED + " : " + e.getMessage();
        }
        return Response.<List<UserActivity>>builder()
                .isSuccess( success )
                .data( retrieveUserActivities )
                .message( message )
                .build();
    }

}
