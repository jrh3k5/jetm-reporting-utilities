package com.google.code.jetm.reporting;

import java.io.Writer;
import java.util.Map;

import etm.core.renderer.MeasurementRenderer;

/**
 * A measurement renderer used to transform the information to XML.
 * 
 * @author jrh3k5
 * 
 */

public class BindingMeasurementRenderer implements MeasurementRenderer {
    private final AggregateBinder binder;
    private final Writer writer;

    /**
     * Create a renderer using the given aggregate binder.
     * 
     * @param binder
     *            The {@link AggregateBinder} to be used.
     * @param writer
     *            The {@link Writer} to which the report data will be written.
     */
    public BindingMeasurementRenderer(AggregateBinder binder, Writer writer) {
        this.binder = binder;
        this.writer = writer;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void render(@SuppressWarnings("rawtypes") Map points) {
        binder.bind(points.values(), writer);
    }
}
