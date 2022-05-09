package com.helthyme.user.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "meal-data")
public class MealData {

    @DynamoDBHashKey(attributeName = "meal_id")
    private String mealId;

    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "date")
    private String date;

    @DynamoDBAttribute(attributeName = "meal_elements")
    private List<String> mealElements;

    @DynamoDBAttribute(attributeName = "calorie_amount")
    private Long calorieAmount;

}
