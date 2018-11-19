package com.rentavehicle.support;

import com.rentavehicle.model.Reservation;
import com.rentavehicle.web.dto.ReservationDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationToReservationDTO implements Converter<Reservation, ReservationDTO>{

	@Override
	public ReservationDTO convert(Reservation reservation) {
		
		if (reservation == null) {
			return null;
		}
		
		ReservationDTO dto = new ReservationDTO();
		
		dto.setId(reservation.getId());
		dto.setStartDate(reservation.getStartDate());
		dto.setEndDate(reservation.getEndDate());
		dto.setTotalPrice(reservation.getTotalPrice());
		dto.setUserId(reservation.getUser().getId());
		dto.setUsername(reservation.getUser().getUsername());
		dto.setVehicleId(reservation.getVehicle().getId());
		dto.setBranchPickupId(reservation.getBranchPickup().getId());
		dto.setBranchDeliveryId(reservation.getBranchDelivery().getId());
		
		return dto;
	}
	
	public List<ReservationDTO> convert(List<Reservation> reservations) {
		List<ReservationDTO> ret = new ArrayList<>();

		for (Reservation reservation : reservations) {
			ret.add(convert(reservation));
		}

		return ret;
	}

}
