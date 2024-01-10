package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.model.dto.ProductResponse;
import com.example.ecommerce.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl {

    @Autowired
    IProductRepo productRepo;

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    ShopServiceImpl shopService;

    @Autowired
    CategoryServiceImpl categoryService;


    public ProductResponse convertProductToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setTitle(product.getTitle());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setStatus(product.getStatus());
        productResponse.setImageMainProduct(product.getImageMainProduct());
        productResponse.setNameCategory(product.getCategory().getName());
        return productResponse;
    }

    public List<Product> findAll() {
        List<Product> productList = (List<Product>) productRepo.findAll();
        return productList;
    }

    public List<Product> findAllByCategoryId(int idCategory) {
        List<Product> productList = productRepo.findAllByCategoryId(idCategory);
        return productList;
    }

    public List<Product> findAllByFilters(String nameSearch, float minPrice, float maxPrice) {
        List<Product> productList = productRepo.findAllByFilters(nameSearch, minPrice, maxPrice);
        return productList;
    }

    public List<ProductResponse> findNewProduct() {
        List<Product> productList = productRepo.findNewProduct();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productList) {
            productResponseList.add(convertProductToProductResponse(product));
        }
        return productResponseList;
    }

    public ProductResponse findProductById(int idProduct) {

        Product product = productRepo.findProductById(idProduct);
        ProductResponse productResponse = convertProductToProductResponse(product);
        return productResponse;
    }

    public Product getProductById(int idProduct) {
        Product product = productRepo.findProductById(idProduct);
        return product;
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public Product findProductByIdProduct(int idProduct) {
        Product product = productRepo.findProductById(idProduct);
        return product;
    }

    public List<ProductResponse> findProductByName(String nameSearch) {
        List<Product> productList = productRepo.findProductByName(nameSearch);

        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productList) {
            productResponseList.add(convertProductToProductResponse(product));
        }
        return productResponseList;
    }

    public List<ProductResponse> findProductByIdShop(int idShop) {
        List<Product> productList = productRepo.findProductByIdShop(idShop);

        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productList) {
            productResponseList.add(convertProductToProductResponse(product));
        }
        return productResponseList;
    }

    public void createProduct(Product product, int idShop) {
        Shop shop = shopService.getShopById(idShop);
        product.setShop(shop);
        productRepo.save(product);
    }

    public void removeProduct(int idProduct) {
        productRepo.deleteById(idProduct);
    }

    public void editProduct(Product product, int idProduct) {
        Product productEdit = productRepo.findProductById(idProduct);
       if (productEdit != null) {
           productEdit.setName(product.getName());
           productEdit.setTitle(product.getTitle());
           productEdit.setPrice(product.getPrice());
           productEdit.setQuantity(product.getQuantity());
           productEdit.setStatus(product.getStatus());
           productEdit.setImageMainProduct(product.getImageMainProduct());
           productEdit.setCategory(product.getCategory());
           productRepo.save(productEdit);
       }
    }

    public List<Product> findProductByOrder(int idOrder) {
        List<Product> productList = productRepo.findProductByOrderDetail(idOrder);
        return productList;
    }
}

