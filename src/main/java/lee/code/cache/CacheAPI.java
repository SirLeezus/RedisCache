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

    //GoldmanChunks Plugin
    public JedisPool getChunksPool() {
        RedisCache plugin = RedisCache.getPlugin();
        return plugin.getChunksPool();
    }

    //GoldmanChunks Plugin
    public JedisPool getNPCPool() {
        RedisCache plugin = RedisCache.getPlugin();
        return plugin.getNpcPool();
    }

    //GoldmanPets Plugin
    public JedisPool getPetPool() {
        RedisCache plugin = RedisCache.getPlugin();
        return plugin.getPetPool();
    }

    //GoldmanTrails Plugin
    public JedisPool getTrailPool() {
        RedisCache plugin = RedisCache.getPlugin();
        return plugin.getTrailPool();
    }
}
