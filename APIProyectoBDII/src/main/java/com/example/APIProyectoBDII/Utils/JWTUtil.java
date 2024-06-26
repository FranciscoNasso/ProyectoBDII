package com.example.APIProyectoBDII.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

public class JWTUtil {

    private static final Key SECRET_KEY = new SecretKeySpec("BDIIBDIIBDIIBDIIBDIIBDIIBDIIBDII".getBytes(),
            SignatureAlgorithm.HS256.getJcaName());
    // es una variable de tipo Key que se genera del string
    // BDIIBDIIBDIIBDIIBDIIBDIIBDIIBDII no puede ser BDII porque tira error, tiene
    // que ser un string de 256 bits

    public static String generarJWT(Integer ci, String contrasenia, boolean esAdmin) {
        long ahorams = System.currentTimeMillis();
        Date ahora = new Date(ahorams);

        long vencimientoms = ahorams + 5 * 24 * 60 * 60 * 1000; // 5 dias en milisegundos
        Date vencimiento = new Date(vencimientoms);

        return Jwts.builder()
                .claim("ci", ci)
                .claim("contrasenia", contrasenia)
                .claim("esAdmin", esAdmin)
                .setIssuedAt(ahora)
                .setExpiration(vencimiento)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String validarToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Boolean esAdmin = claims.get("esAdmin", Boolean.class);
            return esAdmin ? "admin" : "user";
        } catch (ExpiredJwtException e) {
            return "token_expirado";
        } catch (JwtException e) {
            return "invalido";
        }
    }

}
