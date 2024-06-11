package com.favorite.place.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String token = null;
        if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims tokenClaims = jwtTokenProvider.getTokenClaims(token);

        if(jwtTokenProvider.isExpired(tokenClaims)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenClaims);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 사용자 ID 검증
        if (request.getRequestURI().contains("/checking")) {
            String[] pathArray = request.getRequestURI().split("/");
            Long pathUserId = Long.parseLong(pathArray[pathArray.length - 1]);

            if(!tokenClaims.get("userId", Long.class).equals(pathUserId)){
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
