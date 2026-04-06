package com.foodapp.food_ordering.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.foodapp.food_ordering.entity.FoodItem;
import com.foodapp.food_ordering.service.FoodItemService;

@RestController
@RequestMapping("/api/fooditems")


public class FoodItemController {
	
	@Autowired
	private FoodItemService foodItemService;
	
	@GetMapping
	public List<FoodItem> getAllFoodItems() {
		
		return foodItemService.getAllFoodItems();
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FoodItem> getFoodItemById(@PathVariable Long id) {
		
		Optional<FoodItem> foodItem = foodItemService.getFoodItemById(id);
		
		if(foodItem.isPresent()) {
			return ResponseEntity.ok(foodItem.get());
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public FoodItem createFoodItem(@RequestBody FoodItem foodItem) {
		
		return foodItemService.createFoodItem(foodItem);
	}
	
	@PutMapping("/{id}")
	
	public FoodItem updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
		
		return foodItemService.updateFoodItem(id,  foodItem);
	}
	@DeleteMapping("/{id}")
	
	public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) {
		
		foodItemService.deleteFoodItem(id);
		
		return ResponseEntity.ok("Food item deleted successfully!");
	}
	

}
