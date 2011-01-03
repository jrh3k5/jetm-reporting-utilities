package com.google.code.jetm.reporting;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.google.code.jetm.reporting.xml.AggregateBinder;

import etm.core.aggregation.Aggregate;

/**
 * Unit tests for {@link XmlMeasurementRenderer}.
 * 
 * @author jrh3k5
 * 
 */

public class XmlMeasurementRendererTest {
    /**
     * Test the rendering of collected point data.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testRender() {
        final Aggregate aggregate = mock(Aggregate.class);
        final AggregateBinder binder = mock(AggregateBinder.class);
        final Map<String, Aggregate> points = Collections.singletonMap("test", aggregate);
        
        final XmlMeasurementRenderer renderer = new XmlMeasurementRenderer(binder);
        renderer.render(points);
        
        final ArgumentCaptor<Collection> aggregatesCaptor = ArgumentCaptor.forClass(Collection.class);
        verify(binder).marshall(aggregatesCaptor.capture());
        assertThat(aggregatesCaptor.getValue()).containsOnly(aggregate);
    }

}
