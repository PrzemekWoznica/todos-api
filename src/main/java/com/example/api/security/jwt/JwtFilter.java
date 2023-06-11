package com.example.api.security.jwt;

import com.example.api.security.service.UserDetailsServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils;

    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtils.resolveToken(request);
        if(token != null && jwtUtils.validateToken(token)){
            UserDetails userDetails = userDetailsServiceImplementation.loadUserByUsername(jwtUtils.getUsername(token));
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,"", null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
