package com.example.ecommerce.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Data
@AllArgsConstructor
public class AccountToken {
    private int id;
    private String name;
    private String username;
    private String token;
    private String avatar;
    private String password;
    private String apartmentNumber;
    private String ward;
    private String district;
    private String province;
    private Collection<? extends GrantedAuthority> roles;
}
