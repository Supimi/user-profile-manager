package com.helthyme.user.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "activity-data")
public class ActivityData
{

    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "user_id-index", attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "activity_id")
    private String activityId;

    @DynamoDBAttribute(attributeName = "activity_name")
    private String activityName;

    @DynamoDBAttribute(attributeName = "intensity_level")
    private String intensityLevel;

    @DynamoDBAttribute(attributeName = "duration")
    private String duration;

    @DynamoDBAttribute(attributeName = "date")
    private String date;

    @DynamoDBAttribute(attributeName = "calorie")
    private String calorie;

}
