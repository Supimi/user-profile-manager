package com.helthyme.user.controller;

import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserMeal;
import com.helthyme.user.service.UserMealService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userMeal")
public class UserMealController
{
    private final UserMealService userMealService;

    public UserMealController( UserMealService userMealService )
    {
        this.userMealService = userMealService;
    }

    @PostMapping
    public Response<List<UserMeal>> saveUserTakenFoodItems( @RequestBody List<UserMeal> userMealList )
    {
        return this.userMealService.saveUserTakenFoodItems( userMealList );
    }
}