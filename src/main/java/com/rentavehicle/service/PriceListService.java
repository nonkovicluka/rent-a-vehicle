package com.rentavehicle.service;

import com.rentavehicle.model.PriceList;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceListService {

    PriceList findOne(Long id);

    List<PriceList> findAll();

    void save(PriceList priceList);

    List<PriceList> findByAgencyId(Long agencyId);

    PriceList currentPriceList(@Param("agencyId") Long agencyId);
}
