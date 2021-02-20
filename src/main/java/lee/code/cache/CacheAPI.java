package lee.code.cache;

import redis.clients.jedis.JedisPool;

public class CacheAPI {

    //GoldmanShops Plugin
    public JedisPool getShopsPool() {
        RedisCache plugin = RedisCache.getPlugin();
        return plugin.getShopsPool();
    }

    //GoldmanEssentials Plugin
    public JedisPool getEssentialsPool() {
        RedisCache plugin = RedisCache.getPlugin();
        return plugin.getEssentialsPool();
    }
}
