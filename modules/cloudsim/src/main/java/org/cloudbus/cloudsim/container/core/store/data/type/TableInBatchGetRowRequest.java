package org.cloudbus.cloudsim.container.core.store.data.type;

public class TableInBatchGetRowRequest {
    /**
     * 该表的表名。
     */
    private String table_name;
    /**
     * 该行全部的主键列，包含主键名和主键值，由Plainbuffer编码
     */
    private byte[] primary_key;
    /**
     * 宽行读取时指定下一次读取的起始位置，暂不可用。
     */
    private byte[] token;
    /**
     * 该表中需要返回的全部列的列名
     */
    private String columns_to_get;
    /**
     * 是否必要参数：和max_versions必须至少存在一个。
     * 读取数据的版本时间戳范围。
     * 时间戳的单位是毫秒，取值最小值为0，最大值为INT64.MAX。
     * 若要查询一个范围，则指定start_time和end_time。
     * 若要查询一个特定时间戳，则指定specific_time。
     * 例子：如果指定的time_range为(100, 200)，则返回的列数据的时间戳必须位于[100, 200)范围内，前闭后开区间。
     */
    private TimeRange time_range;
    /**
     * 是否必要参数：和time_range必须至少存在一个。
     * 读取数据时，返回的最多版本个数。
     * 例子：如果指定max_versions为2，则每一列最多返回2个版本的数据。
     */
    private int max_versions;
    /**
     * 是否必要参数：否
     * 本次读出的数据是否进入BlockCache。
     * 默认值：true
     * 当前暂不支持设置为false。
     */
    private boolean cache_blocks;
    /**
     * 过滤条件表达式。
     * Filter经过protobuf序列化后的二进制数据。
     */
    private byte[] filter;
    /**
     * 指定读取时的起始列，主要用于宽行读。
     * 返回的结果中包含当前起始列。
     * 列的顺序按照列名的字典序排序。
     * 例子：如果一张表有"a"，"b"，"c"三列，读取时指定start_column为“b”，则会从"b"列开始读，返回"b"，"c"两列。
     */
    private String start_column;
    /**
     * 指定读取时的结束列，主要用于宽行读。
     * 返回的结果中不包含当前结束列。
     * 列的顺序按照列名的字典序排序。
     * 例子：如果一张表有"a"，"b"，"c"三列，读取时指定end_column为“b”，则读到"b"列时会结束，返回"a"列
     */
    private String end_column;
}
