package com.scand.test.repository;

import com.scand.test.model.CoffeeOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderItemRepository extends JpaRepository<CoffeeOrderItem, Long>
{
}
