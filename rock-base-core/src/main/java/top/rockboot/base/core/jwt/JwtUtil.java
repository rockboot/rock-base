package top.rockboot.base.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final long DEFAULT_TOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60;

    private static final String JWT_SECRET = "ThisIsRockBootJwtSecretKey1234567890";
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public static String createJWT(Map<String, Object> claims) {

        final long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(new Date(currentTimeMillis + DEFAULT_TOKEN_EXPIRED_TIME))
                .compact();
    }

    public static Claims verifyJwt(String token) {

        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception ignore) {
        }
        return claims;

    }

}
