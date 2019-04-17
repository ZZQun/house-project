package com.house.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/11.
 */
/*
自动加载属性配置文件的key的前缀，并自动匹配setter属性，注入可以key对应的值,
如：属性声明private String key上相当于加了注解@Value("jwt.config.key")
 */
public class JwtUtil {

    //盐，秘钥
    private String key = "itcast";

    //过期时间的时长，这里推荐一个小时
    private long ttl = 3600000;

    public String getKey() {
        return key;
    }

    public long getTtl() {
        return ttl;
    }


    /**
     * 生成JWT
     *
     * @param id
     * @param subject
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        //当前时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //构建JwtBuilder
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        //有过期时间
        if (ttl > 0) {
            builder.setExpiration( new Date( nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
