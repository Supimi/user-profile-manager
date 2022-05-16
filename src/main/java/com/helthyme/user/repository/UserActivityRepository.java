package com.helthyme.user.repository;

import com.helthyme.user.model.ActivityData;
import org.springframework.data.repository.CrudRepository;

public interface UserActivityRepository extends CrudRepository<ActivityData,String>
{
}
