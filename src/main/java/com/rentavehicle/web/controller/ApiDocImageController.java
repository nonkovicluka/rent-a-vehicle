package com.rentavehicle.web.controller;

import com.rentavehicle.model.DocImage;
import com.rentavehicle.service.DocImageService;
import com.rentavehicle.support.DocImageDTOToDocImage;
import com.rentavehicle.support.DocImageToDocImageDTO;
import com.rentavehicle.web.dto.DocImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/docimages")
public class ApiDocImageController {

	@Autowired
	private DocImageService docImageService;

	@Autowired
	private DocImageDTOToDocImage toDocImage;

	@Autowired
	private DocImageToDocImageDTO toDTO;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DocImageDTO> get(@PathVariable Long id) {
		DocImage docImage = docImageService.findOne(id);

		if (docImage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(docImage), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DocImageDTO> add(@Validated @RequestBody DocImageDTO docImageDTO) {

		DocImage docImage = toDocImage.convert(docImageDTO);
		try {
			docImageService.save(docImage);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(docImage), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<DocImageDTO> edit(@PathVariable Long id, @Validated @RequestBody DocImageDTO editedDocImage) {

		if (id == null || !id.equals(editedDocImage.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		DocImage converted = toDocImage.convert(editedDocImage);

		docImageService.save(converted);

		return new ResponseEntity<>(toDTO.convert(converted), HttpStatus.OK);
	}

}