package com.rentavehicle.support;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.web.dto.PriceListItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceListItemToPriceListItemDTO implements Converter<PriceListItem, PriceListItemDTO> {

	@Override
	public PriceListItemDTO convert(PriceListItem priceListItem) {

		if (priceListItem == null) {
			return null;
		}

		PriceListItemDTO dto = new PriceListItemDTO();

		dto.setId(priceListItem.getId());
		dto.setPricePerHour(priceListItem.getPricePerHour());
		dto.setVehicleId(priceListItem.getVehicle().getId());
		dto.setPriceListId(priceListItem.getPriceList().getId());

		return dto;
	}

	public List<PriceListItemDTO> convert(List<PriceListItem> priceListItems) {
		List<PriceListItemDTO> ret = new ArrayList<>();

		for (PriceListItem priceListItem : priceListItems) {
			ret.add(convert(priceListItem));
		}

		return ret;
	}

}
