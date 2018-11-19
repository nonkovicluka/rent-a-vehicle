package com.rentavehicle.service.impl;

import com.rentavehicle.model.Rating;
import com.rentavehicle.repository.RatingRepository;
import com.rentavehicle.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaRatingService implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating findOne(Long id) {


		return ratingRepository.findOne(id);
	}

	@Override
	public List<Rating> findAll() {

		return ratingRepository.findAll();
	}

	@Override
	public void save(Rating rating) {

		ratingRepository.save(rating);
	}

}
