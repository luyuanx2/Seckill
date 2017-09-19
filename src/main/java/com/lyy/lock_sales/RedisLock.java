package com.lyy.lock_sales;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 基于redis的分布式锁
 * Created by luyuanyuan on 2017/9/19.
 */
public class RedisLock {

    private final long MILLI_NANO_TIME = 100000;

    private Jedis redisClient  = new Jedis("139.224.119.167");

    private String key;

    private String LOCKED;

    private boolean lock;

    public RedisLock(String  lockedPrefix,String objectValue) {
        this.key = lockedPrefix;
        this.LOCKED = objectValue;
    }

    /**
     * 加锁
     * 使用方式为：
     * lock();
     * try{
     * executeMethod();
     * }finally{
     * unlock();
     * }
     *
     * @param timeout timeout的时间范围内轮询锁
     * @param expire  设置锁超时时间
     * @return 成功 or 失败
     */
    public boolean lock(long timeout, int expire) {
        long nanoTime = System.nanoTime();
        timeout *= MILLI_NANO_TIME;
        try {
            //在timeout的时间范围内不断轮询锁
            while (System.nanoTime() - nanoTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (this.redisClient.setnx(this.key, LOCKED) == 1) {
                    this.redisClient.expire(key, expire);//设置锁过期时间是为了在没有释放
                    //锁的情况下锁过期后消失，不会造成永久阻塞
                    this.lock = true;
                    return this.lock;
                }
                System.out.println("出现锁等待");
                //短暂休眠，避免可能的活锁
                Thread.sleep(3, new Random().nextInt(30));
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
        return false;
    }

    public void unlock() {
        try {
            if (this.lock) {
                redisClient.del(key);//直接删除
            }
        } catch (Throwable e) {

        }
    }
}
