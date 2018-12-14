package com.rentavehicle.web.controller;

import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.support.PriceListItemDTOToPriceListItem;
import com.rentavehicle.support.PriceListItemToPriceListItemDTO;
import com.rentavehicle.support.VehiclePliToVehiclePliDTO;
import com.rentavehicle.web.dto.PriceListItemDTO;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import com.rentavehicle.web.dto.VehiclePriceListItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pricelistitems")
public class ApiPriceListItemController {

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private PriceListItemDTOToPriceListItem toPriceListItem;

    @Autowired
    private PriceListItemToPriceListItemDTO toDTO;

    @Autowired
    private VehiclePliToVehiclePliDTO toVehiclePliDTO;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PriceListItemDTO> get(@PathVariable Long id) {
        PriceListItem priceListItem = priceListItemService.findOne(id);

        if (priceListItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(priceListItem), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PriceListItemDTO> add(@Validated @RequestBody PriceListItemDTO priceListItemDTO) {

        PriceListItem priceListItem = toPriceListItem.convert(priceListItemDTO);
        try {
            priceListItemService.save(priceListItem);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(priceListItem), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PriceListItemDTO> edit(@PathVariable Long id,
                                                 @Validated @RequestBody PriceListItemDTO editedPriceListItem) {

        if (id == null || !id.equals(editedPriceListItem.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PriceListItem converted = toPriceListItem.convert(editedPriceListItem);

        priceListItemService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public ResponseEntity<List<VehiclePriceListItemDTO>> currentPriceListItem(@RequestParam Long agencyId, @RequestParam(required = false) String name,
                                                                              @RequestParam(required = false) Long vehicleTypeId,
                                                                              @RequestParam(defaultValue = "0") int pageNum) {

        Page<VehiclePriceListItem> vehiclesAndPrices = priceListItemService.currentPriceLIstItem(agencyId, name, vehicleTypeId, pageNum);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(vehiclesAndPrices.getTotalPages()));
        return new ResponseEntity<>(toVehiclePliDTO.convert(vehiclesAndPrices.getContent()), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/priceListVehicles", method = RequestMethod.GET)
    public ResponseEntity<List<VehiclePriceListItemDTO>> selectedPriceListItem(@RequestParam Long priceListId, @RequestParam Long agencyId, @RequestParam(required = false) String name,
                                                                     @RequestParam(required = false) Long vehicleTypeId,
                                                                     @RequestParam(defaultValue = "0") int pageNum) {

        Page<VehiclePriceListItem> vehiclesAndPrices = priceListItemService.selectedPriceListItem(priceListId, agencyId, name, vehicleTypeId, pageNum);


        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(vehiclesAndPrices.getTotalPages()));
        return new ResponseEntity<>(toVehiclePliDTO.convert(vehiclesAndPrices.getContent()), headers, HttpStatus.OK);
    }

}