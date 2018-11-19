package com.rentavehicle.service;

import com.rentavehicle.model.PriceList;

import java.util.List;

public interface PriceListService {
	
	PriceList findOne(Long id);
	List<PriceList> findAll();
	void save(PriceList priceList);
	
}
