package com.rentavehicle.service.impl;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.repository.PriceListItemRepository;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JpaPriceListItemService implements PriceListItemService {

    @Autowired
    private PriceListItemRepository priceListItemRepository;

    @Override
    public PriceListItem findOne(Long id) {

        return priceListItemRepository.findOne(id);
    }

    @Override
    public List<PriceListItem> findAll() {
        // TODO Auto-generated method stub
        return priceListItemRepository.findAll();
    }

    @Override
    public void save(PriceListItem priceListItem) {
        // TODO Auto-generated method stub
        priceListItemRepository.save(priceListItem);
    }

    @Override
    public List<VehiclePriceListItem> currentPriceLIstItem(Long agencyId, String name, Long vehicleId) {

        if (name != null) {
            name = "%" + name + "%";
        }

        return priceListItemRepository.currentPriceLIstItem(agencyId, name, vehicleId);
    }


}
