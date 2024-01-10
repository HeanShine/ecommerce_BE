package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.model.dto.ShopResponse;
import com.example.ecommerce.repository.IShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl {

    @Autowired
    private IShopRepo shopRepo;

    @Autowired
    private AccountServiceImpl accountService;

    public ShopResponse convertShopToShopResponse(Shop shop) {
        ShopResponse shopResponse = new ShopResponse();
        shopResponse.setId(shop.getId());
        shopResponse.setName(shop.getName());
        shopResponse.setImageShop(shop.getImageShop());
        shopResponse.setAccount(shop.getAccount());
        return shopResponse;
    }

    public List<ShopResponse> getAllShop() {
        List<Shop> shopList = (List<Shop>) shopRepo.findAll();
        List<ShopResponse> shopResponseList = new ArrayList<>();
        for (Shop shop : shopList) {
            shopResponseList.add(convertShopToShopResponse(shop));
        }
        return shopResponseList;
    }

    public int countShopByAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountLogin = accountService.findByUsername(userDetails.getUsername());
        List<Shop> shopList = shopRepo.findByAccount(accountLogin.getId());
        return shopList.size();
    }

    public void createShop(Shop shop) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountLogin = accountService.findByUsername(userDetails.getUsername());
        if (accountLogin != null) {
            if (countShopByAccount() < 4) {
                shop.setAccount(accountLogin);
                shop.setImageShop
                        ("https://cdn.sforum.vn/sforum/wp-content/uploads/2023/10/avatar-facebook-mac-dinh-52.jpg");
                shop.setApartmentNumber("");
                shop.setWard("");
                shop.setDistrict("");
                shopRepo.save(shop);
            }
        }
    }

    public List<ShopResponse> getShopByAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountLogin = accountService.findByUsername(userDetails.getUsername());
        List<Shop> shopList = shopRepo.findByAccount(accountLogin.getId());
        List<ShopResponse> shopResponseList = new ArrayList<>();
        for (Shop shop : shopList) {
            shopResponseList.add(convertShopToShopResponse(shop));
        }
        return shopResponseList;
    }

    public Shop getShopById(int id) {
        Shop shop = shopRepo.findByIdShop(id);
        return shop;
    }
}
