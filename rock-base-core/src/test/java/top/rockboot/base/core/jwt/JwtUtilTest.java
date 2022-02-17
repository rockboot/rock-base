package top.rockboot.base.core.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class JwtUtilTest {

    private String token;

    @BeforeEach
    void ShouldCreateJwt_GivenClaims_WhenCallCreateJwt() {
        final Map<String, Object> map = new HashMap<>();
        map.put("user", "rock");
        map.put("sub", "root");
        token = JwtUtil.createJWT(map);
        Assertions.assertTrue(StringUtils.hasLength(token));
    }

    @Test
    void ShouldVerifyJwt_GivenToken_WhenCallVerifyJwt() {
        final Claims claims = JwtUtil.verifyJwt(token);
        Assertions.assertEquals("root", claims.getSubject());
    }
}
