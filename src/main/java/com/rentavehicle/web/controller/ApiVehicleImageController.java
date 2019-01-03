package com.rentavehicle.web.controller;

import com.rentavehicle.model.VehicleImage;
import com.rentavehicle.service.VehicleImageService;
import com.rentavehicle.support.VehicleImageToVehicleImageDTO;
import com.rentavehicle.web.dto.VehicleImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicleImages")
public class ApiVehicleImageController {

    private static final String FILENAME = "{filename:.+}";

    @Autowired
    private VehicleImageService vehicleImageService;

    @Autowired
    private VehicleImageToVehicleImageDTO toDTO;

//    @RequestMapping(value = "/allByVehicle", method = RequestMethod.GET)
//    public ResponseEntity<List<VehicleImageDTO>> vehicleImages(@RequestParam Long vehicleId) {
//
//        List<VehicleImage> vehicleImages = vehicleImageService.findByVehicleId(vehicleId);
//
//
//        return new ResponseEntity<>(toDTO.convert(vehicleImages), HttpStatus.OK);
//    }

    @RequestMapping(value = "/top3", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleImageDTO>> top3VehicleImages() {

        List<VehicleImage> vehicleImages = vehicleImageService.top3VehicleImages();


        return new ResponseEntity<>(toDTO.convert(vehicleImages), HttpStatus.OK);
    }


    @RequestMapping(value = "/allByAgency", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleImageDTO>> agencyImages(@RequestParam Long agencyId) {

        List<VehicleImage> vehicleImages = vehicleImageService.findByAgencyId(agencyId);


        return new ResponseEntity<>(toDTO.convert(vehicleImages), HttpStatus.OK);
    }


    @RequestMapping(value = "/" + FILENAME, method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable String filename,
                             RedirectAttributes redirectAttributes) {
        try {
            vehicleImageService.deleteImage(filename);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully deleted " + filename);
        } catch (IOException | RuntimeException e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to delete " + filename + " => " + e.getMessage());
        }
        return "redirect:/";
    }
}