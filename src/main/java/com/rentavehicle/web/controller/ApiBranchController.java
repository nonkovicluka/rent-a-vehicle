package com.rentavehicle.web.controller;

import com.rentavehicle.model.Branch;
import com.rentavehicle.service.BranchService;
import com.rentavehicle.support.BranchDTOToBranch;
import com.rentavehicle.support.BranchToBranchDTO;
import com.rentavehicle.web.dto.BranchDTO;
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
@RequestMapping("/api/branches")
public class ApiBranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchDTOToBranch toBranch;

    @Autowired
    private BranchToBranchDTO toDTO;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDTO>> get(@RequestParam(defaultValue = "0") int pageNum) {

        Page<Branch> branches = branchService.findAll(pageNum);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(branches.getTotalPages()));
        return new ResponseEntity<>(toDTO.convert(branches.getContent()), headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BranchDTO> get(@PathVariable Long id) {
        Branch branch = branchService.findOne(id);

        if (branch == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(branch), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<BranchDTO> add(@Validated @RequestBody BranchDTO branchDTO) {

        Branch branch = toBranch.convert(branchDTO);
        try {
            branchService.save(branch);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(toDTO.convert(branch), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BranchDTO> edit(@PathVariable Long id, @Validated @RequestBody BranchDTO editedBranch) {

        if (id == null || !id.equals(editedBranch.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Branch converted = toBranch.convert(editedBranch);

        branchService.save(converted);

        return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
    }

    @RequestMapping(value = "/{agencyId}b", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDTO>> agencyBranches(@PathVariable Long agencyId,
                                                          @RequestParam(defaultValue = "0") int pageNum) {
        Page<Branch> branches = branchService.findByAgencyId(pageNum, agencyId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(branches.getTotalPages()));
        return new ResponseEntity<>(toDTO.convert(branches.getContent()), headers, HttpStatus.OK);
    }

}