package com.example.ecommerce.controller;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.dto.AccountToken;
import com.example.ecommerce.service.impl.AccountServiceImpl;
import com.example.ecommerce.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AccountToken> login(@RequestBody Account account) {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
        Authentication authentication = authenticationManager // ma code nay dung de xac thuc user co ton tai trong database hay khong
                .authenticate(user);
        SecurityContextHolder.getContext().setAuthentication(authentication); // ma code nay dung de set thong tin user vao security context

        String token = tokenService.createToken(account.getUsername()); // ma code nay dung de tao token
        Account currentUser = accountService.findByUsername(account.getUsername()); // ma code nay dung de lay thong tin user tu database

        AccountToken accountToken = new AccountToken(currentUser.getId(), currentUser.getName(),
                currentUser.getUsername(), token, currentUser.getAvatar(), currentUser.getPassword(), currentUser.getApartmentNumber(), currentUser.getWard(), currentUser.getDistrict(), currentUser.getProvince(), currentUser.getRoles());

        return new ResponseEntity<>(accountToken, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        Iterable<Account> listAccount = accountService.findAll();
        for (Account account1 : listAccount) {
            if (account1.getUsername().equals(account.getUsername())) {
                return ResponseEntity.ok("Username already exists");
            }
        }
        accountService.register(account);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Account> changePassword(@RequestParam("currentPassword") String currentPassword,
                                                  @RequestParam("newPassword") String newPassword) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = accountService.findByUsername(userDetails.getUsername());
        if (passwordEncoder.matches(currentPassword, account.getPassword())) {
            account.setPassword(passwordEncoder.encode(newPassword));
            accountService.save(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account1 = accountService.findByUsername(userDetails.getUsername());

       if (account.getAvatar() != null) {
            account1.setAvatar(account.getAvatar());
        }
        if (account.getApartmentNumber() != null) {
            account1.setApartmentNumber(account.getApartmentNumber());
        }
        if (account.getWard() != null) {
            account1.setWard(account.getWard());
        }
        if (account.getDistrict() != null) {
            account1.setDistrict(account.getDistrict());
        }
        if (account.getProvince() != null) {
            account1.setProvince(account.getProvince());
        }
        accountService.save(account1);
        return new ResponseEntity<>(account1, HttpStatus.OK);
    }

    @GetMapping("/getAllAccount")
    public ResponseEntity<List<Account>> getAllAccount() {
        if (accountService.findAll().spliterator().getExactSizeIfKnown() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<Account> accounts = accountService.findAll();
        return new ResponseEntity<>((List<Account>) accounts, HttpStatus.OK);
    }
}
