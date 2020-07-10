package com.link.common.configration.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * Redis工具类
*/
@Component(value="redisService")
public class RedisService
{
	@Resource(name = "redisTemplate")
    private RedisTemplate<String, Object>   redisTemplate;
  
    @Resource(name = "stringValueOperations")
    private ValueOperations<String, String> stringRedisTemplate;

    /**  默认过期时长，单位：秒 */
    public final static long                DEFAULT_EXPIRE = 60 * 60 * 1 ;

    /**  不设置过期时长 */
    public final static long                NOT_EXPIRE     = -1;

    /**
            * 插入缓存默认时间
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value)
    {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 插入缓存
     * @param key 键
     * @param value 值
     * @param expire 过期时间(s)
     */
    public void set(String key, Object value, long expireSenconds)
    {
        set( key, toJson(value) ,expireSenconds ) ; 
    }
    
    /**
		     * 插入缓存默认时间
		* @param key 键
		* @param value 值
		*/
	public void set(String key, String value)
	{
	 set(key, value, DEFAULT_EXPIRE);
	}
    /**
     * 插入缓存
     * @param key 键
     * @param value 值
     * @param expire 过期时间(s)
     */
    public void set(String key, String value, long expireSenconds)
    {
    	//stringRedisTemplate.set(key, value ,expireSenconds );
       	stringRedisTemplate.set(key, value );
        redisTemplate.expire(key, expireSenconds, TimeUnit.SECONDS);
    }
    
    /**
     * 返回字符串结果
     * @param key 键
     * @return
     */
    public String get(String key)
    {
        return stringRedisTemplate.get(key);
    }
    
    /**
            * 返回字符串结果
     * @param key 键
     * @return
     */
    public Boolean expire(String key,long expireSenconds)
    {
        return redisTemplate.expire(key, expireSenconds, TimeUnit.SECONDS);
    }

    /**
     * 返回指定类型结果
     * @param key 键
     * @param clazz 类型class
     * @return
     */
    public <T> T get(String key, Class<T> clazz)
    {
        String value = stringRedisTemplate.get(key);
        return value == null ? null : fromJson(value, clazz);
    }
   
    /**
     * 删除缓存
     * @param key 键
     */
    public void delete(String key)
    {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object)
    {
        if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double
                || object instanceof Boolean || object instanceof String)
        {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz)
    {
        return JSON.parseObject(json, clazz);
    }
}