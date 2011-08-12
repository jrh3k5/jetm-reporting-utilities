package com.google.code.jetm.reporting.ext;

import etm.core.monitor.EtmPoint;

/**
 * An implementation of {@link EtmPoint} that does nothing.
 * 
 * @author JH016266
 * 
 */
public class NoOpEtmPoint implements EtmPoint {
    private String name;

    /**
     * Create a no-op ETM point.
     * 
     * @param name
     *            The name of the point.
     */
    public NoOpEtmPoint(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public void alterName(final String newName) {
        this.name = newName;
    }

    /**
     * {@inheritDoc}
     */
    public void collect() {
    }

    /**
     * {@inheritDoc}
     */
    public long getEndTime() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public EtmPoint getParent() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public long getStartTime() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public long getStartTimeMillis() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public long getTicks() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public double getTransactionTime() {
        return 0;
    }
}
