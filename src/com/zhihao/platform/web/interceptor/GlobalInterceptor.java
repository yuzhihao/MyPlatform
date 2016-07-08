package com.zhihao.platform.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class GlobalInterceptor extends HandlerInterceptorAdapter{
	
	@Value("#{configProperties['redis_url']}")
	private String redis_url; 
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//统计访问用户
		//Connecting to Redis server on localhost
		/*JedisPoolConfig config = new JedisPoolConfig();  
       
		JedisPool pool = new JedisPool(config, redis_url,6379);
		Jedis jedis = pool.getResource(); */
		
		return super.preHandle(request, response, handler);
	}

	
	
}
