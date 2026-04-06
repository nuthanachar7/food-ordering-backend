package com.foodapp.food_ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodapp.food_ordering.entity.FoodItem;

@Repository


public interface FoodItemRepository 
		extends JpaRepository<FoodItem, Long> {
	

}
