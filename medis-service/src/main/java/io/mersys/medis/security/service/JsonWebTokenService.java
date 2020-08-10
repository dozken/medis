package io.mersys.medis.security.service;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.mersys.medis.documents.user.User;
import io.mersys.medis.security.exception.ServiceException;
import io.mersys.medis.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Service
public class JsonWebTokenService implements TokenService {

    private static int tokenExpirationTime = 30;

    @Value("${app.auth.tokenSecret}")
    private String tokenKey;

    private final UserService userDetailsService;

    @Autowired
    public JsonWebTokenService(final UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String getToken(final String username, final String password) {
        if (username == null || password == null) {
            return null;
        }
        final User user = (User) userDetailsService.loadUserByUsername(username);
        Map<String, Object> tokenData = new HashMap<>();
        if (userDetailsService.checkByUserAndPassword(user, password)) {
            tokenData.put("clientType", "user");
            tokenData.put("userID", user.getId());
            tokenData.put("username", user.getUsername());
            tokenData.put("token_create_date", LocalDateTime.now());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, tokenExpirationTime);
            tokenData.put("token_expiration_date", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            return jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact();

        } else {
            throw new ServiceException("Authentication error", this.getClass().getName());
        }
    }

    public static void setTokenExpirationTime(final int tokenExpirationTime) {
        JsonWebTokenService.tokenExpirationTime = tokenExpirationTime;
    }
}
