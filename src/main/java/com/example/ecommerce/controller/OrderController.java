package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.impl.OderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OderServiceImpl oderService;

    @PostMapping("/createOder")
    public ResponseEntity<String> createOrder(@RequestBody Product[] listProduct) {
        oderService.createOder(listProduct);
        return new ResponseEntity<>("Create order successfully", HttpStatus.OK);
    }

    @GetMapping("/findOderByAccount")
    public ResponseEntity<?> findOderByAccount() {
        if (oderService.findOderByAccount().isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oderService.findOderByAccount(), HttpStatus.OK);
    }
}
