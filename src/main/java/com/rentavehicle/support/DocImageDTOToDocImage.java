package com.rentavehicle.support;

import com.rentavehicle.model.DocImage;
import com.rentavehicle.model.User;
import com.rentavehicle.service.DocImageService;
import com.rentavehicle.service.UserService;
import com.rentavehicle.web.dto.DocImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocImageDTOToDocImage implements Converter<DocImageDTO, DocImage> {

	@Autowired
	private DocImageService docImageService;

	@Autowired
	private UserService userService;

	@Override
	public DocImage convert(DocImageDTO dto) {

		DocImage docImage;

		if (dto.getId() == null) {
			User user = userService.findOne(dto.getUserId());
			docImage = new DocImage();
			docImage.setUser(user);
		} else {
			docImage = docImageService.findOne(dto.getId());
		}

		docImage.setImageSource(dto.getImageSource());

		return docImage;
	}

	public List<DocImage> convert(List<DocImageDTO> dtos) {
		List<DocImage> docImages = new ArrayList<>();

		for (DocImageDTO dto : dtos) {
			docImages.add(convert(dto));
		}

		return docImages;
	}

}
