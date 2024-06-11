package com.favorite.place.jwt;

import com.favorite.place.repository.UserRepository;
import com.favorite.place.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey; //JWT 토큰 객체 키를 저장할 시크릿 키
    private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 30;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createJwtToken(Long userId, String userRole) {

        Date now = new Date();
        return Jwts.builder()
                .claim("userId", userId)
                .claim("userRole", userRole)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY))
                .signWith(secretKey)
                .compact();
    }

    public boolean isExpired(Claims tokenClaims) {
        try {
            return tokenClaims.getExpiration().before(new Date());
        } catch (Exception e) {
            throw new IllegalArgumentException("로그인 인증에 실패했습니다. 반복적으로 나타날 시 문의바랍니다.");
        }

    }

    public Authentication getAuthentication(Claims tokenClaims) {
        Long userId = tokenClaims.get("userId", Long.class);
        String userRole = tokenClaims.get("userRole", String.class);

        return new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority(userRole)));
    }

    public Claims getTokenClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }




}
