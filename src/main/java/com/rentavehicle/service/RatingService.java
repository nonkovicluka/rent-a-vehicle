package com.rentavehicle.service;

import com.rentavehicle.model.Rating;

import java.util.List;

public interface RatingService {
	
	Rating findOne(Long id);
	List<Rating> findAll();
	void save(Rating rating);
	
}
