package com.google.code.jetm.reporting.ext;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * A factory to create {@link EtmMonitor} objects. This is to help eliminate annoying error messages when the monitor is used to create points and no monitor has been started.
 * 
 * @author JH016266
 * 
 */

public class PointFactory {
    /**
     * Create a point used for measurement.
     * 
     * @param pointOwner
     *            The {@link Class} of the class that owns the requested point instance.
     * @param methodSignature
     *            The signature of the method in which the point is being used.
     * @return An {@link EtmPoint}.
     */
    public static EtmPoint getPoint(final Class<?> pointOwner, final String methodSignature) {
        final EtmMonitor monitor = EtmManager.getEtmMonitor();
        final String measurementName = pointOwner.getCanonicalName() + ": " + methodSignature;
        return monitor.isStarted() ? monitor.createPoint(measurementName) : new NoOpEtmPoint(measurementName);
    }
}