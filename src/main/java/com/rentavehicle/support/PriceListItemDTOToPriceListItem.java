package com.rentavehicle.support;

import com.rentavehicle.model.PriceList;
import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.model.Vehicle;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.service.PriceListService;
import com.rentavehicle.service.VehicleService;
import com.rentavehicle.web.dto.PriceListItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceListItemDTOToPriceListItem implements Converter<PriceListItemDTO, PriceListItem> {

	@Autowired
	private PriceListService priceListService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private PriceListItemService priceListItemService;

	@Override
	public PriceListItem convert(PriceListItemDTO dto) {

		PriceListItem priceListItem;

		if (dto.getId() == null) {
			Vehicle vehicle = vehicleService.findOne(dto.getVehicleId());
			PriceList priceList = priceListService.findOne(dto.getPriceListId());
			priceListItem = new PriceListItem();
			priceListItem.setVehicle(vehicle);
			priceListItem.setPriceList(priceList);
		} else {
			priceListItem = priceListItemService.findOne(dto.getId());
		}

		priceListItem.setPricePerHour(dto.getPricePerHour());

		return priceListItem;
	}

	public List<PriceListItem> convert(List<PriceListItemDTO> dtos) {
		List<PriceListItem> priceListItems = new ArrayList<>();

		for (PriceListItemDTO dto : dtos) {
			priceListItems.add(convert(dto));
		}

		return priceListItems;
	}

}
