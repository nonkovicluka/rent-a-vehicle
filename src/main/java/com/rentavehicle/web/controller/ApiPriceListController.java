package com.rentavehicle.web.controller;

import com.rentavehicle.model.PriceList;
import com.rentavehicle.service.PriceListService;
import com.rentavehicle.support.PriceListDTOToPriceList;
import com.rentavehicle.support.PriceListToPriceListDTO;
import com.rentavehicle.web.dto.PriceListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pricelists")
public class ApiPriceListController {

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListDTOToPriceList toPriceList;

    @Autowired
    private PriceListToPriceListDTO toDTO;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PriceListDTO> get(@PathVariable Long id) {
        PriceList priceList = priceListService.findOne(id);

        if (priceList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(priceList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PriceListDTO> add(@Validated @RequestBody PriceListDTO priceListDTO) {

        PriceList priceList = toPriceList.convert(priceListDTO);
        try {
            priceListService.save(priceList);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(priceList), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PriceListDTO> edit(@PathVariable Long id,
                                             @Validated @RequestBody PriceListDTO editedPriceList) {

        if (id == null || !id.equals(editedPriceList.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PriceList converted = toPriceList.convert(editedPriceList);

        priceListService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @RequestMapping(value = "/{agencyId}pl", method = RequestMethod.GET)
    public PriceListDTO currentPriceList(@PathVariable Long agencyId) {

        PriceList pl = priceListService.currentPriceList(agencyId);

        return toDTO.convert(pl);

    }
}