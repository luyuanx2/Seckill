package com.lyy.lock_sales.impl;

import com.lyy.lock_sales.LockedObject;
import com.lyy.lock_sales.SeckillInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luyuanyuan on 2017/9/19.
 */
public class SecKillImpl implements SeckillInterface {

    public static Map<Long, Long> inventory ;
    static{
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000l);
        inventory.put(10000002L, 10000l);
    }

    //模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
    public Long reduceInventory(Long commodityId){
        inventory.put(commodityId,inventory.get(commodityId) - 1);
        return inventory.get(commodityId);
    }

    @Override
    public void secKill(String userID, @LockedObject Long commidityID) {
        //最简单的秒杀，这里仅作为demo示例
        reduceInventory(commidityID);
    }
}
