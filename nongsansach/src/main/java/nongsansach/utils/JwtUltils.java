package nongsansach.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nongsansach.dto.RootDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JwtUltils {

    @Value("${key.token.jwt}")
    private String strKey;

    public String createToken(String data){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.builder().subject(data).signWith(secretKey).compact();
    }

    public String decryptToken(String token){
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(strKey));
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (JwtException e) {
            // Xử lý ngoại lệ khi token không hợp lệ
            throw new IllegalArgumentException("Token không hợp lệ");
        }
    }
    public String encodeRootDtoToBase64(RootDTO rootDto) {
        String rootDtoJson = "{ \"email\": \"" + rootDto.getEmail() + "\", \"name\": \"" + rootDto.getName() + "\" }";
        byte[] rootDtoBytes = rootDtoJson.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(rootDtoBytes);
    }
}
