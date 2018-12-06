package com.rentavehicle.service;

import com.rentavehicle.model.Rating;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingService {
	
	Rating findOne(Long id);
	List<Rating> findAll();
	void save(Rating rating);
	Double averageScore(@Param("agencyId") Long agencyId);
	List<Rating> findUserRatings(@Param("agencyId") Long agencyId, @Param("userId") Long userId);
	
}
