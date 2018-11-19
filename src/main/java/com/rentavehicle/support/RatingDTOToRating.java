package com.rentavehicle.support;

import com.rentavehicle.model.Agency;
import com.rentavehicle.model.Rating;
import com.rentavehicle.service.AgencyService;
import com.rentavehicle.service.RatingService;
import com.rentavehicle.web.dto.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RatingDTOToRating implements Converter<RatingDTO, Rating> {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private AgencyService agencyService;

	@Override
	public Rating convert(RatingDTO dto) {

		Rating rating;

		if (dto.getId() == null) {
			Agency agency = agencyService.findOne(dto.getAgencyId());
			rating = new Rating();
			rating.setAgency(agency);
		} else {
			rating = ratingService.findOne(dto.getId());
		}

		rating.setComment(dto.getComment());
		rating.setScore(dto.getScore());

		return rating;
	}

	public List<Rating> convert(List<RatingDTO> dtos) {
		List<Rating> ratings = new ArrayList<>();

		for (RatingDTO dto : dtos) {
			ratings.add(convert(dto));
		}

		return ratings;
	}

}
