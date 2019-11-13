package org.cloudbus.cloudsim.container.core;

import org.cloudbus.cloudsim.container.core.redis.Configuration;
import org.cloudbus.cloudsim.container.core.redis.ERedisModel;
import org.cloudbus.cloudsim.container.core.redis.ERedisOperationType;
import org.cloudbus.cloudsim.container.core.redis.QpsCaculate;
import org.cloudbus.cloudsim.container.schedulers.ContainerCloudletScheduler;

import java.util.List;

public class Redis extends Container {

    private int responseTime;

    private int qps;

    private int max_connection;
    private int max_inbound_bandwidth;
    private int max_outbound_bandwidth;
    private int max_qps;

    private ERedisModel model;

    private int current_connection = 0;

    /**
     * Creates a new Container-based Redis object.
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
    public Redis(int id, int userId, double mips, int numberOfPes, int ram, long bw, long size, String containerManager, ContainerCloudletScheduler containerCloudletScheduler, double schedulingInterval) {
        super(id, userId, mips, numberOfPes, ram, bw, size, containerManager, containerCloudletScheduler, schedulingInterval);
    }

    public Redis(int id, int userId, double mips, int numberOfPes, int ram, long bw, List<ContainerCloudlet> cloudletList) {
        super(id, userId, mips, numberOfPes, ram, bw, cloudletList);
        /**
         * Functions to calculate response time and qps will be added here
         */
        setResponseTime(100);
        setQps(100);

        init();
    }

    private void init() {
        this.max_connection = Configuration.getProperty("redis.MAX_ACTIVE", 1024);
        this.max_inbound_bandwidth = Configuration.getProperty("redis.max.inbound.bandwidth", 1000);
        this.max_outbound_bandwidth = Configuration.getProperty("redis.max.outbound.bandwidth", 1000);
        this.max_qps = Configuration.getProperty("redis.max.qps", 1000);

        this.model = ERedisModel.valueOf(Configuration.getProperty("redis.model", "STANDARDDOUBLE"));
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getQps() {
        return qps;
    }

    private int qps() {
        return QpsCaculate.QpsCaculate(getId(), 0, current_connection, max_connection, getBw()
                , getMips(), model.getStatus(), max_inbound_bandwidth, max_outbound_bandwidth);
    }

    private int qps(ERedisOperationType type) {
        int qps = qps();
        switch (type) {
            default:
                return qps;
        }
    }

    public void setQps(int qps) {
        this.qps = qps;
    }

}
