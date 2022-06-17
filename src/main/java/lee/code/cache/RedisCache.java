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
    @Getter private JedisPool npcPool;
    @Getter private JedisPool petPool;
    @Getter private JedisPool trailPool;
    @Getter private JedisPool guildPool;
    @Getter private Data data;

    @Override
    public void onEnable() {
        this.data = new Data(this);

        Jedis jedis = new Jedis(data.getHostIP(), data.getHostPort(), data.getHostTimeout());
        jedis.auth(data.getHostPass());

        jedis.flushAll();
        JedisPoolConfig poolConfig = buildPoolConfig();

        //GoldmanShops
        this.shopsPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());

        //GoldmanEssentials
        this.essentialsPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());

        //GoldmanChunks
        this.chunksPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());

        //GoldmanNPC
        this.npcPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());

        //GoldmanPets
        this.petPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());

        //GoldmanTrails
        this.trailPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());

        //GoldmanGuilds
        this.guildPool = new JedisPool(poolConfig, data.getHostIP(), data.getHostPort(), data.getHostTimeout(), data.getHostPass());
    }

    private JedisPoolConfig buildPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1000);
        poolConfig.setMaxIdle(32);
        poolConfig.setMaxWait(Duration.ofMillis(100 * 1000));
        poolConfig.setTestOnBorrow(false);
        return poolConfig;
    }

    @Override
    public void onDisable() {
        shopsPool.destroy();
        essentialsPool.destroy();
        chunksPool.destroy();
        npcPool.destroy();
        petPool.destroy();
        trailPool.destroy();
        guildPool.destroy();
    }

    public static RedisCache getPlugin() {
        return RedisCache.getPlugin(RedisCache.class);
    }
}
