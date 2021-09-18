package com.scand.test.repository;

import com.scand.test.model.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, Long>
{
    List<CoffeeType> findAllByOrderByPriceAsc();
}
