package com.helthyme.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserActivity
{
    private String id;
    private String userId;
    private String activityId;
    private String activityName;
    private String intensityLevel;
    private String duration;
    private String date;
    private String calorie;
}
