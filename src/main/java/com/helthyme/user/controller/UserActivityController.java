package com.helthyme.user.controller;

import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserActivity;
import com.helthyme.user.service.UserActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userActivity")
@CrossOrigin
public class UserActivityController
{
    private final UserActivityService userActivityService;

    public UserActivityController( UserActivityService userActivityService )
    {
        this.userActivityService = userActivityService;
    }

    @PostMapping
    public Response<List<UserActivity>> saveUserActivities( @RequestBody List<UserActivity> userActivities )
    {
        return this.userActivityService.saveUserActivities( userActivities );
    }

    @GetMapping
    public Response<List<UserActivity>> getUserActivitiesByUserIdAndDate( @RequestParam(value = "userId", required = true) String userId )
    {
        return this.userActivityService.getUserActivitiesByUserIdAndDate( userId );
    }
}
