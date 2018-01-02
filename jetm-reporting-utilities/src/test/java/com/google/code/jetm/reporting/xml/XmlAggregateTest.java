package com.google.code.jetm.reporting.xml;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link XmlAggregate}.
 *
 * @author jrh3k5
 *
 */

public class XmlAggregateTest {
    /**
     * Test the computation of averages.
     */
    @Test
    public void testGetAverage() {
        final long measurements = 32;
        final double total = 23.0;
        final XmlAggregate aggregate = new XmlAggregate(0, 0, total, measurements, "test");
        assertThat(aggregate.getAverage()).isEqualTo(total / measurements);
    }

    /**
     * If the aggregate has 0 measurements, it should return 0 for the average.
     */
    @Test
    public void testGetAverageNoMeasurements() {
        final XmlAggregate aggregate = new XmlAggregate(1, 1, 23, 0, "test");
        assertThat(aggregate.getAverage()).isZero();
    }

}
