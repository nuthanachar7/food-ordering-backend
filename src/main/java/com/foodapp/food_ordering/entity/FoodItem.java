package com.foodapp.food_ordering.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "food_items")



public class FoodItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String name;
	private Double price;
	private String description;
	private String category;
	
	public Long getId() { return id; }
	public String getName() { return name; }
	public Double getPrice() { return price; }
	public String getDescription() { return description; }
	public String getCategory() { return category; }
	
	public void setId(Long id) {this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setPrice(Double price) { this.price = price; }
	public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    
}







