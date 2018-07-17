package com.jwt;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    public static String encode(){
        JwtBuilder jwsBuilder = Jwts.builder();
        /**
         * iss: token的发行者
         * sub: token的题目
         * aud: token的客户
         * exp: 经常使用的，以数字时间定义失效期，也就是当前时间以后的某个时间本token失效。
         * nbf: 定义在此时间之前，JWT不会接受处理。
         * iat: JWT发布时间，能用于决定JWT年龄
         * jti: JWT唯一标识. 能用于防止 JWT重复使用，一次只用一个token
         */
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("iss","scl");
        map.put("sub","jwt");
        jwsBuilder.addClaims(map)
                .setId("jid")
                .setSubject("subject")
                .signWith(SignatureAlgorithm.HS256,generalKey());

        return jwsBuilder.compact();

    }

    public static Claims decode(String token){
        JwtParser jwsParser = Jwts.parser();
        Claims claims = jwsParser.setSigningKey(generalKey()).parseClaimsJws(token).getBody();
        return claims;
    }

    public static SecretKey generalKey(){
        String stringKey = "sucl";//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
 //       System.out.println(encodedKey);//[B@152f6e2
 //       System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//7786df7fc3a34e26a61c034d5ec8245d
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return key;
    }

    public static void main(String[] args) {
        SecretKey key = generalKey();
        System.out.println("key : " + key.toString());
        String token = encode();
        System.out.println("token : " + token);

        Claims claims = decode(token);

        System.out.println( claims.get("iss") + " , " +claims.get("sub") );
    }
}
