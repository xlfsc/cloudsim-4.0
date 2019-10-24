package org.cloudbus.cloudsim.container.core.store.data.type;

public class TimeRange {
    /**
     * 起始时间戳。单位是毫秒。时间戳的取值最小值为0，最大值为INT64.MAX
     */
    private int start_time = 1;
    /**
     * 结束时间戳。单位是毫秒。时间戳的取值最小值为0，最大值为INT64.MAX
     */
    private int end_time = 2;
    /**
     * 特定的时间戳值。specific_time和[start_time, end_time) 两个中设置一个即可。单位是毫秒。时间戳的取值最小值为0，最大值为INT64.MAX
     */
    private int specific_time = 3;
}
