package com.rentavehicle.support;

import com.rentavehicle.model.PriceList;
import com.rentavehicle.web.dto.PriceListDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceListToPriceListDTO implements Converter<PriceList, PriceListDTO> {

	@Override
	public PriceListDTO convert(PriceList priceList) {

		if (priceList == null) {
			return null;
		}

		PriceListDTO dto = new PriceListDTO();

		dto.setId(priceList.getId());
		dto.setStartDate(priceList.getStartDate());
		dto.setEndDate(priceList.getEndDate());
		dto.setAgencyId(priceList.getAgency().getId());
		dto.setAgencyName(priceList.getAgency().getName());

		return dto;

	}

	public List<PriceListDTO> convert(List<PriceList> priceLists) {
		List<PriceListDTO> ret = new ArrayList<>();

		for (PriceList priceList : priceLists) {
			ret.add(convert(priceList));
		}

		return ret;
	}

}
