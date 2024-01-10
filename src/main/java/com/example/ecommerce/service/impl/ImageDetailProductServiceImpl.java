package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.ImageDetailProduct;
import com.example.ecommerce.model.dto.ImageDetailProductResponse;
import com.example.ecommerce.repository.IImageDetailProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageDetailProductServiceImpl {
    @Autowired
    IImageDetailProductRepo imageDetailProductRepo;

    public ImageDetailProductResponse convertImageDetailProductToImageDetailProductResponse(ImageDetailProduct imageDetailProduct){
        ImageDetailProductResponse imageDetailProductResponse = new ImageDetailProductResponse();
        imageDetailProductResponse.setId(imageDetailProduct.getId());
        imageDetailProductResponse.setImageDetailProduct(imageDetailProduct.getImageDetailProduct());
        imageDetailProductResponse.setProductId(imageDetailProduct.getProduct().getId());
        return imageDetailProductResponse;
    }

    public List<ImageDetailProductResponse> findAllByIdProduct(int idProduct){
        List<ImageDetailProduct> imageDetailProductList = imageDetailProductRepo.findAllByIdProduct(idProduct);
        List<ImageDetailProductResponse> imageDetailProductResponseList = new ArrayList<>();
        for (ImageDetailProduct imageDetailProduct : imageDetailProductList) {
            imageDetailProductResponseList.add(convertImageDetailProductToImageDetailProductResponse(imageDetailProduct));
        }
        return imageDetailProductResponseList;
    }
}
