package com.rentavehicle.web.controller;

import com.rentavehicle.model.Agency;
import com.rentavehicle.service.AgencyService;
import com.rentavehicle.support.AgencyDTOToAgency;
import com.rentavehicle.support.AgencyToAgencyDTO;
import com.rentavehicle.web.dto.AgencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class ApiAgencyController {

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private AgencyDTOToAgency toAgency;

    @Autowired
    private AgencyToAgencyDTO toDTO;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<AgencyDTO>> get(@RequestParam(defaultValue = "0") int pageNum) {

        Page<Agency> agencies = agencyService.findAll(pageNum);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(agencies.getTotalPages()));
        return new ResponseEntity<>(toDTO.convert(agencies.getContent()), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{agencyId}", method = RequestMethod.GET)
    public ResponseEntity<AgencyDTO> get(@PathVariable Long agencyId) {
        Agency agency = agencyService.findOne(agencyId);

        if (agency == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(agency), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<AgencyDTO> add(@Validated @RequestBody AgencyDTO agencyDTO) {

        Agency agency = toAgency.convert(agencyDTO);
        try {
            agencyService.save(agency);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(agency), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<AgencyDTO> edit(@PathVariable Long id, @Validated @RequestBody AgencyDTO editedAgency) {

        if (id == null || !id.equals(editedAgency.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Agency converted = toAgency.convert(editedAgency);

        agencyService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}uPages", method = RequestMethod.GET)
    public ResponseEntity<List<AgencyDTO>> ownerAgencies(@PathVariable Long userId,
                                                         @RequestParam(defaultValue = "0") int pageNum) {

        Page<Agency> agencies = agencyService.findByOwnerId(pageNum, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(agencies.getTotalPages()));
        return new ResponseEntity<>(toDTO.convert(agencies.getContent()), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}u", method = RequestMethod.GET)
    public ResponseEntity<List<AgencyDTO>> ownerAgencies(@PathVariable Long userId) {

        List<Agency> agencies = agencyService.findByOwnerId(userId);


        return new ResponseEntity<>(toDTO.convert(agencies), HttpStatus.OK);
    }

}