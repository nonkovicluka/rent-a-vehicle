package com.rentavehicle.service.impl;

import com.rentavehicle.model.PriceList;
import com.rentavehicle.repository.PriceListRepository;
import com.rentavehicle.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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

}
