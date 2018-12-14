package com.rentavehicle.service.impl;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.repository.PriceListItemRepository;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Page<VehiclePriceListItem> currentPriceLIstItem(Long agencyId, String name, Long vehicleTypeId, int pageNum) {

        if (name != null) {
            name = "%" + name + "%";
        }

        return priceListItemRepository.currentPriceLIstItem(agencyId, name, vehicleTypeId, new PageRequest(pageNum, 6));
    }

    @Override
    public Page<VehiclePriceListItem> selectedPriceListItem(Long priceListId, Long agencyId, String name, Long vehicleTypeId, int pageNum) {
        if (name != null) {
            name = "%" + name + "%";
        }

        return priceListItemRepository.selectedPriceListItem(priceListId, agencyId, name, vehicleTypeId, new PageRequest(pageNum, 6));
    }

    @Override
    public List<VehiclePriceListItem> current(Long agencyId, String name, Long vehicleTypeId) {

        if (name != null) {
            name = "%" + name + "%";
        }

        return priceListItemRepository.current(agencyId, name, vehicleTypeId);
    }
}
