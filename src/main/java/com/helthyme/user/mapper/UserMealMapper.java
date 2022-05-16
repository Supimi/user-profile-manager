package com.helthyme.user.mapper;

import com.helthyme.user.dto.UserMeal;
import com.helthyme.user.model.MealData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMealMapper
{
    public abstract UserMeal mealDataToUserMeal( MealData mealData );

    public abstract MealData userMealToMealData( UserMeal userActivity );

    public abstract List<MealData> userMealListToMealDataList( List<UserMeal> userActivities );

    public abstract List<UserMeal> mealDataListToUserMealList( List<MealData> mealDataList );
}
