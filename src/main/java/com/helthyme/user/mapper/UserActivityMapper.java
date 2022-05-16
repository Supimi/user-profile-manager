package com.helthyme.user.mapper;

import com.helthyme.user.dto.UserActivity;
import com.helthyme.user.model.ActivityData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserActivityMapper
{
    public abstract UserActivity activityDataToUserActivity( ActivityData activityData );

    public abstract ActivityData userActivityToActivityData( UserActivity userActivity );

    public abstract List<ActivityData> userActivityListToActivityDataList( List<UserActivity> userActivities );

    public abstract List<UserActivity> activityDataListToUserActivityList( List<ActivityData> activityDataList );
}
