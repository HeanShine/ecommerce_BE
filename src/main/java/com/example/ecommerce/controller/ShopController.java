package com.example.ecommerce.controller;

import com.example.ecommerce.model.Shop;
import com.example.ecommerce.model.dto.ShopResponse;
import com.example.ecommerce.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopServiceImpl shopService;

    @GetMapping
    public ResponseEntity<List<ShopResponse>> getAllShop() {
        if (shopService.getAllShop().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ShopResponse> shopResponseList = shopService.getAllShop();
        return new ResponseEntity<>(shopResponseList, HttpStatus.OK);
    }

    @PostMapping("/createShop")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        if (shopService.countShopByAccount() >= 4) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        shopService.createShop(shop);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @GetMapping("/getShopByAccount")
    public ResponseEntity<List<ShopResponse>> getShopByAccount() {
        if (shopService.getShopByAccount().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ShopResponse> shopResponseList = shopService.getShopByAccount();
        return new ResponseEntity<>(shopResponseList, HttpStatus.OK);
    }
}
