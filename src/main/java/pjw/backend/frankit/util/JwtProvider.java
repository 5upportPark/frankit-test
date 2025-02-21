package pjw.backend.frankit.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pjw.backend.frankit.exception.TokenException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    private static SecretKey secretKey;
    private static final long TOKEN_EXP = 24*60*60*1000;
    private static final String BEARER_TYPE = "Bearer";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    public JwtProvider(@Value("${jwt.key}") String key){
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public static String create(Long id, String name, String email){
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, id);
        claims.put(NAME, name);
        claims.put(EMAIL, email);
        return Jwts.builder()
                .issuer("issuer")
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(currentTimeMillis+TOKEN_EXP))
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    public static boolean isValidate(String token){
        if(StringUtils.isBlank(token)) return false;
        token = splitToken(token);
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            throw new TokenException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new TokenException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new TokenException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        } catch (Exception e){
            throw new TokenException(e);
        }
    }

    private static String splitToken(String token){
        if(StringUtils.isBlank(token)) return token;

        String result = "";
        if(token.startsWith(BEARER_TYPE)){
            result = token.split(" ")[1];
        }
        return result;
    }
}
