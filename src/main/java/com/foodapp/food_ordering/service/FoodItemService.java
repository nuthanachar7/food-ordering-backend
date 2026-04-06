package com.foodapp.food_ordering.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapp.food_ordering.entity.FoodItem;
import com.foodapp.food_ordering.repository.FoodItemRepository;

@Service


public class FoodItemService {
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	public List<FoodItem> getAllFoodItems() {
		
		return foodItemRepository.findAll();
	}
	public Optional<FoodItem> getFoodItemById(Long id) {
		
		return foodItemRepository.findById(id);
		
		
	}
	public FoodItem createFoodItem(FoodItem foodItem) {
		
		return foodItemRepository.save(foodItem);

}
	public FoodItem updateFoodItem(Long id, FoodItem updatedItem) {
		
		FoodItem existingItem = foodItemRepository.findById(id).get();
		
		existingItem.setName(updatedItem.getName());
		existingItem.setPrice(updatedItem.getPrice());
		existingItem.setDescription(updatedItem.getDescription());
		existingItem.setCategory(updatedItem.getCategory());
		
		return foodItemRepository.save(existingItem);
	}
	
	public void deleteFoodItem(Long id) {
		
		foodItemRepository.deleteById(id);
	}
}
