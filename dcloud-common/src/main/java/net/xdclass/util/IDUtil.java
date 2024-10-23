package net.xdclass.util;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * 使用雪花算法 生成唯一ID
 **/

public class IDUtil {


    private static SnowflakeShardingKeyGenerator shardingKeyGenerator = new SnowflakeShardingKeyGenerator();

    /**
     * 雪花算法生成器
     * @return
     */
    public static   Comparable<?> geneSnowFlakeID(){

        return shardingKeyGenerator.generateKey();
    }

}