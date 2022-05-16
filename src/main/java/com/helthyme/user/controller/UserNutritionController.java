package com.helthyme.user.controller;

import com.helthyme.user.domain.Response;
import com.helthyme.user.dto.UserNutrition;
import com.helthyme.user.service.UserNutritionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userNutrition")
public class UserNutritionController
{
    private final UserNutritionService userNutritionService;

    public UserNutritionController( UserNutritionService userNutritionService )
    {
        this.userNutritionService = userNutritionService;
    }

    @PostMapping
    public Response<List<UserNutrition>> saveUserTakenFoodItems( @RequestBody List<UserNutrition> userNutritionList )
    {
        return this.userNutritionService.saveUserTakenFoodItems( userNutritionList );
    }
}
