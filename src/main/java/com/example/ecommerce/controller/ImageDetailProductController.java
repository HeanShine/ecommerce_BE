package com.example.ecommerce.controller;

import com.example.ecommerce.model.ImageDetailProduct;
import com.example.ecommerce.model.dto.ImageDetailProductResponse;
import com.example.ecommerce.service.impl.ImageDetailProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/imageDetailProduct")
public class ImageDetailProductController {

    @Autowired
    ImageDetailProductServiceImpl imageDetailProductService;

    @RequestMapping("/{idProduct}")
    public ResponseEntity<ImageDetailProductResponse> getImageDetailProductByIdProduct(@PathVariable int idProduct) {
        List<ImageDetailProductResponse> imageDetailProductResponseList = imageDetailProductService.findAllByIdProduct(idProduct);
        if (imageDetailProductResponseList.isEmpty()) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(imageDetailProductResponseList, org.springframework.http.HttpStatus.OK);
    }
}
