package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.dto.ProductResponse;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/findAllByIdCategory/{idCategory}")
    public ResponseEntity<List<Product>> findAllByCategoryId(@PathVariable int idCategory) {
        if (productService.findAllByCategoryId(idCategory).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Product> productList = productService.findAllByCategoryId(idCategory);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        if (productService.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findNewProduct")
    public ResponseEntity<List<ProductResponse>> findNewProduct() {
        if (productService.findNewProduct().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ProductResponse> productList = productService.findNewProduct();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findAllByFilters")
    public ResponseEntity<List<Product>> findAllByFilters(@RequestParam String nameSearch,
                                                          @RequestParam float minPrice,
                                                          @RequestParam float maxPrice) {
        if (nameSearch.equals("")) {
            nameSearch = null;
        }
        if (maxPrice == 0) {
            maxPrice = Float.MAX_VALUE;
        }
        List<Product> productList = productService.findAllByFilters(nameSearch, minPrice, maxPrice);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/searchProductByName")
    public ResponseEntity<List<ProductResponse>> searchProductByName(@RequestParam String nameSearch) {
        if (nameSearch.equals("")) {
            nameSearch = null;
        }
        List<ProductResponse> productList = productService.findProductByName(nameSearch);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/findProductById/{idProduct}")
    public ResponseEntity<Product> findProductById(@PathVariable int idProduct) {
        Product product = productService.findProductByIdProduct(idProduct);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/findProductByShopId")
    public ResponseEntity<List<ProductResponse>> findProductByShopId(@RequestParam int idShop) {
        List<ProductResponse> productList = productService.findProductByIdShop(idShop);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/createProduct/{idShop}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @PathVariable int idShop) {
        productService.createProduct(product, idShop);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/removeProduct/{idProduct}")
    public ResponseEntity<String> removeProduct(@PathVariable int idProduct) {
        productService.removeProduct(idProduct);
        return new ResponseEntity<>("Remove product successfully", HttpStatus.OK);
    }

    @PostMapping("/updateProduct/{idProduct}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable int idProduct) {
        productService.editProduct(product, idProduct);
        return new ResponseEntity<>("Update product successfully", HttpStatus.OK);
    }

    @GetMapping("/findProductByOrderId/{idOrder}")
    public ResponseEntity<List<Product>> findProductByOrderId(@PathVariable int idOrder) {
        List<Product> productList = productService.findProductByOrder(idOrder);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
