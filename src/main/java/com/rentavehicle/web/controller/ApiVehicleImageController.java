package com.rentavehicle.web.controller;

import com.rentavehicle.service.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/api/vehicleImages")
public class ApiVehicleImageController {

    private static final String FILENAME = "{filename:.+}";

    @Autowired
    private VehicleImageService vehicleImageService;


    @RequestMapping(value = "/" + FILENAME + "/raw", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> oneRawImage(@PathVariable String filename) {

        try {
            Resource file = vehicleImageService.findOneImage(filename);
            return ResponseEntity.ok()
                    .contentLength(file.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Couldn't find " + filename + " => " + e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public String createFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            vehicleImageService.createImage(file);
            redirectAttributes.addFlashAttribute("flash.message", "Successfully uploaded " + file.getName());
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("flash.message", "Failed to upload " + file.getName() + " => " + e.getMessage());
        }
        return "redirect:/";

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