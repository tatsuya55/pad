package com.pad.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import springfox.documentation.builders.BuilderDefaults;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class JwtUtils {
    //设置token过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    //指定的秘钥
    private static final String secretKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";

    /**
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
     */
    public static void main(String[] args) throws Exception {
        //生成jwt令牌
        /*JwtBuilder jwtBuilder = Jwts.builder()
                .setId("66")//设置jwt编码
                .setSubject("程序员")//设置jwt主题
                .setIssuedAt(new Date())//设置jwt签发日期
                //.setExpiration(date)//设置jwt的过期时间
                .claim("t", "admin")
                .claim("company", "itheima")
                .signWith(SignatureAlgorithm.HS256, secretKey);
        String jwtToken = jwtBuilder.compact();
        System.out.println(jwtToken);*/

        /*//解析jwt,得到其内部的数据
        byte[] bytes = DatatypeConverter.parseBase64Binary(secretKey);
        Claims claims = Jwts.parser().setSigningKey(bytes).parseClaimsJws(s).getBody();
        System.out.println(claims);*/

      /*  //生成jwt
        String s = acquireJWT("123456");
        System.out.println(s);*/

        /*//解析jwt
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5NWQwMjJjMmUxYzM0ZDM3OGRjZWI1NWQ1MDgzMDhhMSIsInN1YiI6IjEyMzQ1NiIsImlhdCI6MTY2MjE2NTk5NywiZXhwIjoxNjYyMjUyMzk3fQ.92ogsKHTpECIBTqkKHhZz7o8aSADXtv8Rl08FXzzThw");
        System.out.println(claims);*/
    }

    /**
     *生成串
     * @param id
     * @return
     * @throws Exception
     */
    public static String acquireJWT(String id,String subject,String uid)  {
        //生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)//设置jwt jti: jwt的唯一身份标识，主要用来作为一次性token
                .setSubject(subject)//设置jwt主题 sub: jwt所面向的用户
                .setIssuedAt(new Date())//设置jwt签发日期 iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//设置jwt的过期时间 exp: jwt的过期时间
                .claim("uid",uid)
              //.claim("company", "itheima")
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return jwtBuilder.compact();
    }

    //通过用户id生成jwt
    public static String acquireJWT(String id)  {
        //生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUIDUtils.getSimpleUUID())//设置jwt jti: jwt的唯一身份标识，主要用来作为一次性token
                .setSubject(id)//设置jwt主题 sub: jwt所面向的用户
                .setIssuedAt(new Date())//设置jwt签发日期 iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//设置jwt的过期时间 exp: jwt的过期时间
                //.claim("uid",uid)
                //.claim("company", "itheima")
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return jwtBuilder.compact();
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}

