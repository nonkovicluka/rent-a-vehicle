package com.rentavehicle.support;

import com.rentavehicle.model.DocImage;
import com.rentavehicle.web.dto.DocImageDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocImageToDocImageDTO implements Converter<DocImage, DocImageDTO> {

	@Override
	public DocImageDTO convert(DocImage docImage) {

		if (docImage == null) {
			return null;
		}

		DocImageDTO dto = new DocImageDTO();

		dto.setId(docImage.getId());
		dto.setImageSource(docImage.getImageSource());
		dto.setUserId(docImage.getUser().getId());
		dto.setUsername(docImage.getUser().getUsername());

		return dto;
	}

	public List<DocImageDTO> convert(List<DocImage> docImages) {
		List<DocImageDTO> ret = new ArrayList<>();

		for (DocImage docImage : docImages) {
			ret.add(convert(docImage));
		}

		return ret;
	}

}
