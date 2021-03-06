package com.helthyme.user.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
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
@DynamoDBTable(tableName = "meal-data")
public class MealData
{

    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "food")
    private String food;

    @DynamoDBAttribute(attributeName = "date_value")
    private String dateValue;

    @DynamoDBAttribute(attributeName = "calorie")
    private String calorie;

}
