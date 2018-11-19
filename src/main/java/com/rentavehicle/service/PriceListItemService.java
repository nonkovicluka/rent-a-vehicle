package com.rentavehicle.service;

import com.rentavehicle.model.PriceListItem;

import java.util.List;

public interface PriceListItemService {
	
	PriceListItem findOne(Long id);
	List<PriceListItem> findAll();
	void save(PriceListItem priceListItem);
	
}
