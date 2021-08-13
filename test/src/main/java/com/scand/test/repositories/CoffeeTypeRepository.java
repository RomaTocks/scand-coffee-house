package com.scand.test.repositories;

import com.scand.test.models.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, Integer>
{
}
