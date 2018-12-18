package com.rentavehicle.web.controller;

import com.rentavehicle.model.BranchImage;
import com.rentavehicle.service.BranchImageService;
import com.rentavehicle.support.BranchImageToBranchImageDTO;
import com.rentavehicle.web.dto.BranchImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/branchImages")
public class ApiBranchImageController {

    private static final String FILENAME = "{filename:.+}";

    @Autowired
    private BranchImageService branchImageService;

    @Autowired
    private BranchImageToBranchImageDTO toDTO;

    @RequestMapping(value = "/allByAgency", method = RequestMethod.GET)
    public ResponseEntity<List<BranchImageDTO>> agencyImages(@RequestParam Long agencyId) {

        List<BranchImage> branchImages = branchImageService.findByAgencyId(agencyId);


        return new ResponseEntity<>(toDTO.convert(branchImages), HttpStatus.OK);
    }


    @RequestMapping(value = "/" + FILENAME, method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable String filename,
                             RedirectAttributes redirectAttributes) {
        try {
            branchImageService.deleteImage(filename);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully deleted " + filename);
        } catch (IOException | RuntimeException e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to delete " + filename + " => " + e.getMessage());
        }
        return "redirect:/";
    }

}