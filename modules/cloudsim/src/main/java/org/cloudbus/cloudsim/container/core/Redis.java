package org.cloudbus.cloudsim.container.core;

import org.cloudbus.cloudsim.container.schedulers.ContainerCloudletScheduler;

import java.util.List;

public class Redis extends Container {

    private int responseTime;

    private int qps;

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

    public void setQps(int qps) {
        this.qps = qps;
    }

}
