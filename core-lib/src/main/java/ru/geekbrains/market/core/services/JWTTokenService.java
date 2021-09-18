package ru.geekbrains.market.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.core.models.UserInfo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class JWTTokenService implements ITokenService {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Override
    public String generateToken(UserInfo userInfo) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);
        String compactTokenString = Jwts.builder()
                .claim("id", userInfo.getId())
                .claim("sub", userInfo.getLogin())
                .claim("roles", userInfo.getRoles())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return "Bearer " + compactTokenString;
    }

    @Override
    public UserInfo parseToken(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);
        String userLogin = jwsClaims.getBody().getSubject();
        Long userId = jwsClaims.getBody().get("id", Long.class);
        List<String> userRoles = jwsClaims.getBody().get("roles", List.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setLogin(userLogin);
        userInfo.setRoles(userRoles);
        return userInfo;
    }
}
