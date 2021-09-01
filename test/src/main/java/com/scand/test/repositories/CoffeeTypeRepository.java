package com.scand.test.repositories;

import com.scand.test.models.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, Integer>
{
    List<CoffeeType> findAllByOrderByPriceAsc();
}
