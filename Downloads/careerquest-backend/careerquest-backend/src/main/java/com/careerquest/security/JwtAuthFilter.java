package com.careerquest.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");

        String path = req.getRequestURI();

        System.out.println("PATH = " + path);
        System.out.println("AUTH HEADER = " + authHeader);
        // Allow public APIs
        if (path.contains("/api/users/login") ||
                path.contains("/api/users/register")) {
            chain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Missing or Invalid Token");
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = JwtUtil.extractUsername(token);

            System.out.println("Email from token: " + email);

            req.setAttribute("email", email);

        } catch (Exception e) {

            e.printStackTrace();   // ADD THIS

            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Invalid Token");
            return;
        }

        chain.doFilter(request, response);
    }
}