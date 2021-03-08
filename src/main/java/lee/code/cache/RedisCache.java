package lee.code.cache;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache extends JavaPlugin {

    @Getter private JedisPool shopsPool;
    @Getter private JedisPool essentialsPool;
    @Getter private JedisPool chunksPool;
    @Getter private JedisPool npcPool;

    @Override
    public void onEnable() {

        Jedis jedis = new Jedis("184.95.51.250", 6380, 5000);

        jedis.auth("3NBtQUaUNnsxp5XMWp9AAE6d794ncecP2cV7m5HsBA8NrusWFj");
        jedis.flushAll();

        JedisPoolConfig poolConfig = buildPoolConfig();

        //GoldmanShops
        this.shopsPool = new JedisPool(poolConfig, "184.95.51.250", 6380, 5000, "3NBtQUaUNnsxp5XMWp9AAE6d794ncecP2cV7m5HsBA8NrusWFj");

        //GoldmanEssentials
        this.essentialsPool = new JedisPool(poolConfig, "184.95.51.250", 6380, 5000, "3NBtQUaUNnsxp5XMWp9AAE6d794ncecP2cV7m5HsBA8NrusWFj");

        //GoldmanChunks
        this.chunksPool = new JedisPool(poolConfig, "184.95.51.250", 6380, 5000, "3NBtQUaUNnsxp5XMWp9AAE6d794ncecP2cV7m5HsBA8NrusWFj");

        //GoldmanNPC
        this.npcPool = new JedisPool(poolConfig, "184.95.51.250", 6380, 5000, "3NBtQUaUNnsxp5XMWp9AAE6d794ncecP2cV7m5HsBA8NrusWFj");
    }

    private JedisPoolConfig buildPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1000);
        poolConfig.setMaxIdle(32);
        poolConfig.setMaxWaitMillis(100 * 1000);
        poolConfig.setTestOnBorrow(false);
        return poolConfig;
    }

    @Override
    public void onDisable() {
        shopsPool.destroy();
        essentialsPool.destroy();
        chunksPool.destroy();
        npcPool.destroy();
    }

    public static RedisCache getPlugin() {
        return RedisCache.getPlugin(RedisCache.class);
    }
}
