package com.example.ecommerce.security.jwt;

import com.example.ecommerce.service.impl.AccountServiceImpl;
import com.example.ecommerce.service.impl.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    // dung de filter request cua client gui len server,
    // kiem tra xem co token hay khong,
    // neu co thi kiem tra token co hop le hay khong,
    // neu hop le thi set user vao security context  de su dung cho cac request tiep theo
    // (neu khong thi tra ve loi 401)

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountServiceImpl accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (jwt != null && tokenService.validateJwtToken(jwt)) {
            // lay thong tin user tu token
            String username = tokenService.getUserNameFromJwtToken(jwt);
            // lay thong tin user tu database
            UserDetails userDetails = accountService.loadUserByUsername(username);
            // set thong tin user vao security context
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // ma code nay dung de set thong tin user vao security context
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // ma code nay dung de set thong tin user vao security context
           // ma code nay dung de set thong tin user vao security context
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}