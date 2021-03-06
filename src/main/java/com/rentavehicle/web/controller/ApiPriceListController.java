package com.rentavehicle.web.controller;

import com.rentavehicle.model.PriceList;
import com.rentavehicle.model.PriceListItem;
import com.rentavehicle.service.PriceListItemService;
import com.rentavehicle.service.PriceListService;
import com.rentavehicle.support.PriceListDTOToPriceList;
import com.rentavehicle.support.PriceListToPriceListDTO;
import com.rentavehicle.web.dto.PriceListDTO;
import com.rentavehicle.web.dto.VehiclePriceListItem;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pricelists")
public class ApiPriceListController {

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListItemService priceListItemService;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<PriceListDTO> add(@Validated @RequestBody PriceListDTO priceListDTO) {

        PriceList priceList = toPriceList.convert(priceListDTO);
        LocalDate startDate = priceList.getStartDate().plusDays(1);
        LocalDate endDate = priceList.getEndDate().plusDays(1);
        PriceList currentPriceList = priceListService.currentPriceList(priceListDTO.getAgencyId());


        LocalDate now = new LocalDate().minusDays(1);

        if (startDate.isBefore(endDate) && startDate.isAfter(now)) {
            if (currentPriceList != null) {
                if (startDate.isBefore(currentPriceList.getEndDate())) {

                    currentPriceList.setEndDate(startDate.minusDays(1));
                }


                String name = null;
                Long vehicleTypeId = null;
                List<VehiclePriceListItem> vehiclePriceListItems = priceListItemService.current(priceListDTO.getAgencyId(), name, vehicleTypeId);
                List<PriceListItem> priceListItems = new ArrayList<>();

                for (VehiclePriceListItem vehiclePli : vehiclePriceListItems) {
                    PriceListItem pli = new PriceListItem();
                    pli.setPricePerHour(vehiclePli.getPriceListItem().getPricePerHour());
                    pli.setPriceList(priceList);
                    pli.setVehicle(vehiclePli.getPriceListItem().getVehicle());
                    priceListItems.add(pli);
                }


                priceList.setPriceListItems(priceListItems);
            }

            priceListService.save(priceList);

        } else {
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

    @RequestMapping(value = "/{agencyId}/pl", method = RequestMethod.GET)
    public PriceListDTO currentPriceList(@PathVariable Long agencyId) {

        PriceList pl = priceListService.currentPriceList(agencyId);

        return toDTO.convert(pl);

    }

    @RequestMapping(value = "/{agencyId}pl", method = RequestMethod.GET)
    public List<PriceListDTO> agencyPriceLists(@PathVariable Long agencyId) {
        List<PriceList> priceLists = priceListService.findByAgencyId(agencyId);


        return toDTO.convert(priceLists);
    }

    @RequestMapping(value = "/last-price-list", method = RequestMethod.GET)
    public PriceListDTO lastAddedPriceList(@RequestParam Long agencyId) {
        PriceList priceList = priceListService.lastAddedPriceList(agencyId);


        return toDTO.convert(priceList);
    }
}