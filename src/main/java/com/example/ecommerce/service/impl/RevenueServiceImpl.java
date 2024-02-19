package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.Revenue;
import com.example.ecommerce.repository.IRevenueRepo;
import com.example.ecommerce.repository.OderRepo;
import com.example.ecommerce.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private IRevenueRepo revenueRepo;

    @Autowired
    private OderRepo oderRepo;

    @Override
    public List<Revenue> findAll() {
        return revenueRepo.findAll();
    }

    @Override
    public void save(Revenue revenue) {
        revenueRepo.save(revenue);
    }

    @Override
    public void edit(Revenue revenue) {
        revenueRepo.save(revenue);
    }

    @Override
    public void delete(int id) {
        revenueRepo.deleteById(id);
    }

    @Override
    public Revenue findByTeamId(int id) {
        return revenueRepo.findById(id).orElse(null);
    }

    @Override
    public void addMonthlyRevenue(Account account) {

    }

    @Override
    public List<Revenue> findByAccount(Account account) {
        return revenueRepo.findByAccount(account);
    }

    @Override
    public List<Revenue> getYearlyRevenue(Account account) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstDayOfYear = calendar.getTime();
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date lastDayOfYear = calendar.getTime();
        return revenueRepo.findAllByAccountAndTimeBetween(account, firstDayOfYear, lastDayOfYear);
    }
}
