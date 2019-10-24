package org.cloudbus.cloudsim.container.core.store.data.type;

public class Response {
    /**
     * 本次操作消耗的服务能力单元
     */
    private ConsumedCapacity consumed;

    private byte[] rows;
    private byte[] next_start_primary_key;
}
