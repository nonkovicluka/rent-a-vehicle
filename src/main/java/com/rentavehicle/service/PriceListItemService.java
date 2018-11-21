package com.rentavehicle.service;

import com.rentavehicle.model.PriceListItem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceListItemService {

    PriceListItem findOne(Long id);

    List<PriceListItem> findAll();

    void save(PriceListItem priceListItem);

    PriceListItem currentPriceLIstItem(@Param("priceListId") Long priceListId, @Param("vehicleId") Long vehicleId);


}
