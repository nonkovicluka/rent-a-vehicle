package com.rentavehicle.web.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentavehicle.model.Branch;
import com.rentavehicle.service.BranchImageService;
import com.rentavehicle.service.BranchService;
import com.rentavehicle.support.BranchDTOToBranch;
import com.rentavehicle.support.BranchToBranchDTO;
import com.rentavehicle.web.dto.BranchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class ApiBranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchImageService branchImageService;

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

    @RequestMapping(value = "/addAll", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity<?> addAll(
            @RequestParam(required = false) MultipartFile[] branchImages, @RequestParam String branchDTO) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        BranchDTO dto = mapper.readValue(branchDTO, BranchDTO.class);

        Branch branch = toBranch.convert(dto);

        if (branch != null) {
            branchService.save(branch);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (branchImages.length > 0) {
            for (MultipartFile branchImage : branchImages) {
                try {
                    branchImageService.createImage(branchImage, branch, branch.getAgency().getId());
                } catch (IOException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

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

    @RequestMapping(value = "/{agencyId}bPages", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDTO>> agencyBranches(@PathVariable Long agencyId,
                                                          @RequestParam(defaultValue = "0") int pageNum) {
        Page<Branch> branches = branchService.findByAgencyId(pageNum, agencyId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(branches.getTotalPages()));
        return new ResponseEntity<>(toDTO.convert(branches.getContent()), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{agencyId}b", method = RequestMethod.GET)
    public ResponseEntity<List<BranchDTO>> agencyBranches(@PathVariable Long agencyId) {

        List<Branch> branches = branchService.findByAgencyId(agencyId);

        return new ResponseEntity<>(toDTO.convert(branches), HttpStatus.OK);
    }

}