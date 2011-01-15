package com.google.code.jetm.reporting;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

import etm.core.aggregation.Aggregate;

/**
 * Definition of an object used to bind and un-bind aggregate data.
 * 
 * @author jrh3k5
 * 
 */

public interface AggregateBinder {
    /**
     * Bind aggregate data.
     * 
     * @param aggregates
     *            A {@link Collection} of {@link Aggregate} objects representing
     *            the data to be bound.
     * @param writer
     *            The {@link Writer} to which the bound data will be written.
     */
    void bind(Collection<? extends Aggregate> aggregates, Writer writer);

    /**
     * Unbind aggregate data.
     * 
     * @param reader
     *            A {@link Reader} representing a source of
     *            {@link #bind(Collection, Writer) bound} aggregate data to be
     *            un-bound.
     * @return A {@link Collection} of {@link Aggregate} objects representing
     *         the data to be unbound.
     */
    Collection<Aggregate> unbind(Reader reader);
}
