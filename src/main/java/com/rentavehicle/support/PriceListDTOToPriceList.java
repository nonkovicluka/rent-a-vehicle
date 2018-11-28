package com.rentavehicle.support;

import com.rentavehicle.model.Agency;
import com.rentavehicle.model.PriceList;
import com.rentavehicle.service.AgencyService;
import com.rentavehicle.service.PriceListService;
import com.rentavehicle.web.dto.PriceListDTO;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceListDTOToPriceList implements Converter<PriceListDTO, PriceList> {

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private AgencyService agencyService;

    @Override
    public PriceList convert(PriceListDTO dto) {

        PriceList priceList;

        if (dto.getId() == null) {
            Agency agency = agencyService.findOne(dto.getAgencyId());
            priceList = new PriceList();
            priceList.setAgency(agency);
        } else {
            priceList = priceListService.findOne(dto.getId());
        }

        String inputStart = dto.getStartDate();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDate dtStart = formatter.parseLocalDate(inputStart);


        String inputEnd = dto.getEndDate();

        LocalDate dtEnd = formatter.parseLocalDate(inputEnd);


        priceList.setStartDate(dtStart);
        priceList.setEndDate(dtEnd);

        return priceList;
    }

    public List<PriceList> convert(List<PriceListDTO> dtos) {
        List<PriceList> priceLists = new ArrayList<>();

        for (PriceListDTO dto : dtos) {
            priceLists.add(convert(dto));
        }

        return priceLists;
    }

}
