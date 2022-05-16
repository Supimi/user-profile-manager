package com.helthyme.user.repository;

import com.helthyme.user.model.MealData;
import org.springframework.data.repository.CrudRepository;

public interface UserMealRepository extends CrudRepository<MealData,String>
{
}
