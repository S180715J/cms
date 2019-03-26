package com.newer.cms.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.newer.cms.pojo.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

/**
 * 
 * 牛耳教育,180801J: 创建JWT工具类 <br>
 * 
 * @author 朱璐 2019年3月4日
 */
@Component
public class JwtTokenUtil {

	private Clock clock = DefaultClock.INSTANCE;

	/**
	 * 秘钥，从application.properties中读取
	 */
	@Value("${jwt.secret}")
	private static final String SECRET = "zhulu";
	/**
	 * jwt失效时间（单位为秒）
	 */
	@Value("${jwt.expiration}")
	private long expiration = 1800L;

	/**
	 * 创建jwt token
	 * 
	 * @param uid
	 * @param lastTime
	 * @param pwd
	 * @param aid
	 * @return
	 */
	public String creatJwt(String uname, Integer uid, Integer rid) {
		Date now = clock.now();
		// 添加JWT的包含部分：有三个部分：头部（header）。截荷（payload),签证（sinnatrue）,
		// 设置jwt创建时间
		JwtBuilder jwtBuilder = Jwts.builder().setHeaderParam("typ", "JWT").setIssuedAt(now).setIssuer(uname) // 设置当前用户信息
				.setExpiration(calculateExpirationDate(now)) // 设置失效时间
				.setSubject("uid").claim("uname", uname).claim("uid", uid).claim("role", rid)
				.signWith(SignatureAlgorithm.HS512, SECRET); // 设置加密算法，指定秘钥

		return jwtBuilder.compact();
	}

	/**
	 * 验证JWT
	 * 
	 * @param token
	 * @return
	 */
	public Claims parseJWT(String token) {
		// 根据秘钥解析token，解析成功返回claims;解析不成功会抛出异常
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return claims;
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + expiration * 1000);
	}
}
