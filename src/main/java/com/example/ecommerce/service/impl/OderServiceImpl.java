package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.Orders;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.dto.OrderDetailResponse;
import com.example.ecommerce.repository.OderDetailRepo;
import com.example.ecommerce.repository.OderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OderServiceImpl {

    @Autowired
    private OderRepo oderRepo;

    @Autowired
    private OderDetailRepo oderDetailRepo;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private ProductServiceImpl productService;

    public double getTotalPayment(Product[] products) {
        double totalPayment = 0.0;
        for (Product product : products) {
            totalPayment += product.getPrice() * product.getQuantity();
        }
        return totalPayment;
    }


    public void createOder(Product[] listProduct) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountLogin = accountService.findByUsername(userDetails.getUsername());

        Orders orders = new Orders();
        orders.setDatetime(new Date(System.currentTimeMillis()));
        orders.setTotalPayment(getTotalPayment(listProduct));
        orders.setAccount(accountLogin);
        oderRepo.save(orders);

        for (Product product : listProduct) {
            Product productUpdate = productService.getProductById(product.getId());

            if (!"DORMANT".equals(productUpdate.getStatus())) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrders(orders);
                orderDetail.setProduct(product);
                orderDetail.setPrice(product.getPrice());
                orderDetail.setQuantity(product.getQuantity());

                productUpdate.setQuantity(productUpdate.getQuantity() - product.getQuantity());
                productService.updateProduct(productUpdate);

                if (productUpdate.getQuantity() == 0) {
                    productUpdate.setStatus("DORMANT");
                    productService.updateProduct(productUpdate);
                }

                oderDetailRepo.save(orderDetail);
            } else {
                System.out.println("Sản phẩm " + productUpdate.getName() + " đã ngừng kinh doanh");
            }
        }
    }


    public OrderDetailResponse convertOrderDetail(OrderDetail orderDetail) {

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setIdProduct(orderDetail.getProduct().getId());
        orderDetailResponse.setNameProduct(orderDetail.getProduct().getName());
        orderDetailResponse.setPrice(orderDetail.getPrice());
        orderDetailResponse.setQuantity(orderDetail.getQuantity());
        orderDetailResponse.setImageProduct(orderDetail.getProduct().getImageMainProduct());
        orderDetailResponse.setTotal(orderDetail.getOrders().getTotalPayment());
        return orderDetailResponse;
    }

    public List<OrderDetailResponse> getOderDetail(int idOrder) {
        List<OrderDetail> orderDetailList = oderDetailRepo.findAllByIdOrder(idOrder);
        List<OrderDetailResponse> orderDetailResponseList = new java.util.ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetailResponseList.add(convertOrderDetail(orderDetail));
        }
        return orderDetailResponseList;
    }

    public List<Orders> findOderByAccount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountLogin = accountService.findByUsername(userDetails.getUsername());
        return oderRepo.findOderByAccount(accountLogin.getId());
    }
}
