package com.rentavehicle.web.controller;

import com.rentavehicle.model.Rating;
import com.rentavehicle.model.Reservation;
import com.rentavehicle.model.User;
import com.rentavehicle.service.RatingService;
import com.rentavehicle.service.ReservationService;
import com.rentavehicle.support.RatingDTOToRating;
import com.rentavehicle.support.RatingToRatingDTO;
import com.rentavehicle.web.dto.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class ApiRatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RatingDTOToRating toRating;

    @Autowired
    private RatingToRatingDTO toDTO;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RatingDTO> get(@PathVariable Long id) {
        Rating rating = ratingService.findOne(id);

        if (rating == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(rating), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<RatingDTO> add(@Validated @RequestBody RatingDTO ratingDTO, @RequestParam Long userId, @RequestParam Long agencyId) {

        Rating rating = toRating.convert(ratingDTO);

        List<Reservation> reservations = reservationService.findFinishedReservation(agencyId, userId);


        if (reservations.size() > 0) {
            ratingService.save(rating);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(rating), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RatingDTO> edit(@PathVariable Long id, @Validated @RequestBody RatingDTO editedRating) {

        if (id == null || !id.equals(editedRating.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Rating converted = toRating.convert(editedRating);

        ratingService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgScore", method = RequestMethod.GET)
    public Double getAverageScore(@RequestParam Long agencyId) {

        return ratingService.averageScore(agencyId);
    }

}