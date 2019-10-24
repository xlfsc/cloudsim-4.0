package org.cloudbus.cloudsim.container.core.store;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @param <K> 主键类型
 * @param <A> 属性列数据类型
 */
public class WideColumnRow<K, A> {

    /**
     * 主键，类型为：Binary，String，Integer
     */
    private List<K> primaryKey;

    /**
     * 属性列，每列对应多个值，不同值对应的key为版本
     * 数据类型为：String，Binary，Double，Integer，Boolean
     */
    private List<Map<Timestamp, A>> attributeColumn;


    /**
     * 生命周期
     */
    private int timeToLive;

    /**
     * 最大版本数
     */
    private int maxVersions;


    /**
     * 主键的第一列称为分区键
     *
     * @return 分区键
     */
    public K getPartitionKey() {
        return primaryKey.get(0);
    }


}

