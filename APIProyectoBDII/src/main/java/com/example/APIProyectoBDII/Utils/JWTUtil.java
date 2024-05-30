package com.example.APIProyectoBDII.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;


public class JWTUtil {

    private static final Key SECRET_KEY = new SecretKeySpec("BDII".getBytes(), SignatureAlgorithm.HS256.getJcaName()); // es una variable de tipo Key que se genera del string BDII :)

    public static String generarJWT(Integer ci, String contrasenia, boolean esAdmin) {
        long ahorams = System.currentTimeMillis();
        Date ahora = new Date(ahorams);

        long vencimientoms = ahorams + 5*24*60*60*1000; // 5 dias en milisegundos
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
}
