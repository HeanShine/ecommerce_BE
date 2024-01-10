package com.example.ecommerce.controller;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.dto.OrderDetailResponse;
import com.example.ecommerce.service.impl.OderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OderServiceImpl oderService;

    @GetMapping("/getOderDetailByOrder/{idOrder}")
    public ResponseEntity<List<OrderDetailResponse>> getOderDetailByOrder(@PathVariable int idOrder) {
        if (oderService.getOderDetail(idOrder).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(oderService.getOderDetail(idOrder), HttpStatus.OK);
    }
}
