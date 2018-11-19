package com.rentavehicle.support;

import com.rentavehicle.model.Rating;
import com.rentavehicle.web.dto.RatingDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RatingToRatingDTO implements Converter<Rating, RatingDTO> {

	@Override
	public RatingDTO convert(Rating rating) {

		if (rating == null) {
			return null;
		}

		RatingDTO dto = new RatingDTO();

		dto.setId(rating.getId());
		dto.setScore(rating.getScore());
		dto.setComment(rating.getComment());
		dto.setAgencyId(rating.getAgency().getId());
		dto.setAgencyName(rating.getAgency().getName());

		return dto;
	}

	public List<RatingDTO> convert(List<Rating> ratings) {
		List<RatingDTO> ret = new ArrayList<>();

		for (Rating rating : ratings) {
			ret.add(convert(rating));
		}

		return ret;
	}

}
