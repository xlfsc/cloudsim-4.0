package org.cloudbus.cloudsim.container.core.store.data.type;

public class RowInBatchGetRowResponse {
    /**
     * 该行操作是否成功。若为 true，则该行读取成功，error 无效；若为 false，则该行读取失败，row 无效。
     */
    private boolean is_ok;
    /**
     * 该行操作的错误信息
     */
    private Error error;
    /**
     * 该行操作消耗的服务能力单元
     */
    private ConsumedCapacity consumed;
    /**
     * 读取到的数据，由Plainbuffer编码，详见Plainbuffer编码。
     * 如果该行不存在，则数据为空。
     */
    private byte[] row;
    /**
     * 宽行读取时，下一次读取的起始位置，暂不可用。
     */
    private byte[] next_token;
}
