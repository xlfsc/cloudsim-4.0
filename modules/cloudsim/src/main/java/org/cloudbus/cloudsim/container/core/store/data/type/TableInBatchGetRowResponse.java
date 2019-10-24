package org.cloudbus.cloudsim.container.core.store.data.type;

public class TableInBatchGetRowResponse {

    /**
     * 该表的表名
     */
    private String table_name;
    /**
     * 该表中读取到的全部行数据
     */
    private RowInBatchGetRowResponse rows;
}
