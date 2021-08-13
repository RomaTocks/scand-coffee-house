package com.scand.test.repositories;

import com.scand.test.models.CoffeeOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderItemRepository extends JpaRepository<CoffeeOrderItem, Long>
{
}
