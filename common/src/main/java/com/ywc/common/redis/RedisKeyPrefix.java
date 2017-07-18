package com.ywc.common.redis;

/**
 * 
 * @Title: RedisKeyPrefix.java  
 * @Package com.cardpay.redis    
 * @Description: TODO 用户记录redisKey前缀。存储模块化
 * @author Leo
 * @date 2016年6月7日 下午4:12:46
 */
public enum RedisKeyPrefix {
	// following are the component names
	COMMON ("CM"),
	USER ("USR"),
	PRODUCT ("PRODUCT"),
	OTHER ("OTH"),
	CFS ("CFS");
	
	private final String prefix;
	   
	RedisKeyPrefix(String prefix) {
	     this.prefix = prefix;
	}
	
	public String getKeyPrefix() {
	     return prefix;
	}
}
