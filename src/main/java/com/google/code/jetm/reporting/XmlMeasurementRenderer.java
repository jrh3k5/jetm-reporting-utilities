package com.google.code.jetm.reporting;

import java.util.Map;

import com.google.code.jetm.reporting.xml.AggregateBinder;

import etm.core.renderer.MeasurementRenderer;

/**
 * A measurement renderer used to transform the information to XML.
 * 
 * @author jrh3k5
 * 
 */

public class XmlMeasurementRenderer implements MeasurementRenderer {
    private final AggregateBinder binder;

    /**
     * Create a renderer.
     */
    public XmlMeasurementRenderer() {
        this(new AggregateBinder());
    }

    /**
     * Create a renderer using the given aggregate binder.
     * 
     * @param binder
     *            The {@link AggregateBinder} to be used.
     */
    public XmlMeasurementRenderer(AggregateBinder binder) {
        this.binder = binder;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void render(@SuppressWarnings("rawtypes") Map points) {
        binder.marshall(points.values());
    }

}
