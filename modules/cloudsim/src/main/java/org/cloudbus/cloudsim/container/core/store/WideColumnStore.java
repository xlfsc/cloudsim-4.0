package org.cloudbus.cloudsim.container.core.store;

import java.util.List;

public class WideColumnStore {

    private String name;


    private double capacity;

    private double currentSize;

    /**
     * 网络带宽
     */
    double bandwidth;

    /**
     * 网络延迟
     */
    double networkLatency;

    private List<WideColumnTable> tables;
}
