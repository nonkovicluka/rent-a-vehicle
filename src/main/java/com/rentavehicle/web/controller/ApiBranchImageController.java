package com.rentavehicle.web.controller;

import com.rentavehicle.model.BranchImage;
import com.rentavehicle.service.BranchImageService;
import com.rentavehicle.support.BranchImageDTOToBranchImage;
import com.rentavehicle.support.BranchImageToBranchImageDTO;
import com.rentavehicle.web.dto.BranchImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branchimages")
public class ApiBranchImageController {

	@Autowired
	private BranchImageService branchImageService;

	@Autowired
	private BranchImageDTOToBranchImage toBranchImage;

	@Autowired
	private BranchImageToBranchImageDTO toDTO;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BranchImageDTO> get(@PathVariable Long id) {
		BranchImage branchImage = branchImageService.findOne(id);

		if (branchImage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(branchImage), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BranchImageDTO> add(@Validated @RequestBody BranchImageDTO branchImageDTO) {

		BranchImage branchImage = toBranchImage.convert(branchImageDTO);
		try {
			branchImageService.save(branchImage);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(branchImage), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<BranchImageDTO> edit(@PathVariable Long id,
			@Validated @RequestBody BranchImageDTO editedBranchImage) {

		if (id == null || !id.equals(editedBranchImage.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		BranchImage converted = toBranchImage.convert(editedBranchImage);

		branchImageService.save(converted);

		return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
	}

}