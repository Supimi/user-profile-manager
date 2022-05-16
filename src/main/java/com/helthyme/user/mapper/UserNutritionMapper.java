package com.helthyme.user.mapper;

import com.helthyme.user.dto.UserNutrition;
import com.helthyme.user.model.NutritionData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserNutritionMapper
{
    public abstract UserNutrition nutritionDataToUserNutrition( NutritionData nutritionData );

    public abstract NutritionData userNutritionToNutritionData( UserNutrition userActivity );

    public abstract List<NutritionData> userNutritionListToNutritionDataList( List<UserNutrition> userActivities );

    public abstract List<UserNutrition> nutritionDataListToUserNutritionList( List<NutritionData> nutritionDataList );
}
