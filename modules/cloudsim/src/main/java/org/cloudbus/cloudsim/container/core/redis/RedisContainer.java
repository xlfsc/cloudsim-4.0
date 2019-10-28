package org.cloudbus.cloudsim.container.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.cloudbus.cloudsim.container.core.Container;
import org.cloudbus.cloudsim.container.schedulers.ContainerCloudletScheduler;

public class RedisContainer extends Container {

    double accessTime;
    double responseTime;

    /**
     * Creates a new Container object.
     *
     * @param id
     * @param userId
     * @param mips
     * @param numberOfPes
     * @param ram
     * @param bw
     * @param size
     * @param containerManager
     * @param containerCloudletScheduler
     * @param schedulingInterval
     */
    public RedisContainer(int id, int userId, double mips, int numberOfPes, int ram, long bw, long size, String containerManager, ContainerCloudletScheduler containerCloudletScheduler, double schedulingInterval) {
        super(id, userId, mips, numberOfPes, ram, bw, size, containerManager, containerCloudletScheduler, schedulingInterval);
    }

    public double getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(double accessTime) {
        this.accessTime = accessTime;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public boolean accessRedis() {
        return false;
    }

    public String getValue(String key) {
        return StringUtils.EMPTY;
    }

    public void put(String key, String value) {

    }
}
