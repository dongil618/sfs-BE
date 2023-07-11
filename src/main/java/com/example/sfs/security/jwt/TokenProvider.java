package com.example.sfs.security.jwt;

import com.example.sfs.model.Member;
import com.example.sfs.model.MemberAuthority;
import com.example.sfs.security.auth.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

//    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String MEMBER_ID_KEY = "memberId";
    private static final String USERNAME_KEY = "userName";
    private static final String AUTHORITIES_KEY = "role";
    private static final String SUBJECT = "SFS";
    private final String base64Secret;
    private final long tokenValidityInMilliseconds;
    private final long tokenValidityInMillisecondsForRememberMe;

    private Key key;


    public TokenProvider(
            @Value("${jwt.base64-secret}") String base64Secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
            @Value("${jwt.token-validity-in-seconds-for-remember-me}") long tokenValidityInSecondsForRememberMe) {
        this.base64Secret = base64Secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.tokenValidityInMillisecondsForRememberMe = tokenValidityInSecondsForRememberMe * 1000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Member member, Set<MemberAuthority> memberAuthorities, boolean rememberMe) {
        String authorities = memberAuthorities.stream()
                .map(memberAuthority -> memberAuthority.getAuthority().getRole())
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }

        return Jwts.builder()
                .setSubject(SUBJECT)
                .claim(MEMBER_ID_KEY, String.valueOf(member.getId()))
                .claim(USERNAME_KEY, member.getUsername())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) getAuthorities(claims);
        Long memberId = Long.valueOf(claims.get(MEMBER_ID_KEY).toString());
        String username = claims.get(USERNAME_KEY).toString();
        Member member = new Member(memberId, username);
        UserDetailsImpl userDetails = new UserDetailsImpl(member, authorities);

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), authorities);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return authorities;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info("Invalid JWT signature.");
//            log.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
//            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
//            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
//            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}