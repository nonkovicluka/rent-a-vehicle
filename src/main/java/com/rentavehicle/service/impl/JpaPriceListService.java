package com.rentavehicle.service.impl;

import com.rentavehicle.model.PriceList;
import com.rentavehicle.repository.PriceListRepository;
import com.rentavehicle.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaPriceListService implements PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Override
    public PriceList findOne(Long id) {


        return priceListRepository.findOne(id);
    }

    @Override
    public List<PriceList> findAll() {
        // TODO Auto-generated method stub
        return priceListRepository.findAll();
    }

    @Override
    public void save(PriceList priceList) {
        // TODO Auto-generated method stub
        priceListRepository.save(priceList);
    }

    @Override
    public List<PriceList> findByAgencyId(Long agencyId) {

        return priceListRepository.findByAgencyId(agencyId);
    }

    @Override
    public PriceList currentPriceList(Long agencyId) {

        return priceListRepository.currentPriceList(agencyId);
    }

    @Override
    public PriceList lastAddedPriceList(Long agencyId) {

        return priceListRepository.lastAddedPriceList(agencyId);
    }

}
