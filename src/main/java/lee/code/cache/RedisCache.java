package lee.code.cache;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class RedisCache extends JavaPlugin {

    @Getter private JedisPool shopsPool;
    @Getter private JedisPool essentialsPool;
    @Getter private JedisPool chunksPool;

    @Override
    public void onEnable() {
        Jedis jedis = new Jedis("localhost");
        jedis.flushAll();

        JedisPoolConfig poolConfig = buildPoolConfig();

        //GoldmanShops
        this.shopsPool = new JedisPool(poolConfig, "localhost");

        //GoldmanEssentials
        this.essentialsPool = new JedisPool(poolConfig, "localhost");

        //GoldmanChunks
        this.chunksPool = new JedisPool(poolConfig, "localhost");
    }

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    @Override
    public void onDisable() {
        shopsPool.destroy();
        essentialsPool.destroy();
        chunksPool.destroy();
    }

    public static RedisCache getPlugin() {
        return RedisCache.getPlugin(RedisCache.class);
    }
}
