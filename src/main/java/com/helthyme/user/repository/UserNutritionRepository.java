package com.helthyme.user.repository;

import com.helthyme.user.model.NutritionData;
import org.springframework.data.repository.CrudRepository;

public interface UserNutritionRepository extends CrudRepository<NutritionData,String>
{
}
