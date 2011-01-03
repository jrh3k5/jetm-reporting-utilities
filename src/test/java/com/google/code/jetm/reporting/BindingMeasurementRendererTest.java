package com.google.code.jetm.reporting;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import etm.core.aggregation.Aggregate;

/**
 * Unit tests for {@link BindingMeasurementRenderer}.
 * 
 * @author jrh3k5
 * 
 */

public class BindingMeasurementRendererTest {
    /**
     * Test the rendering of collected point data.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testRender() {
        final StringWriter writer = mock(StringWriter.class);
        final Aggregate aggregate = mock(Aggregate.class);
        final AggregateBinder binder = mock(AggregateBinder.class);
        final Map<String, Aggregate> points = Collections.singletonMap("test", aggregate);
        
        final BindingMeasurementRenderer renderer = new BindingMeasurementRenderer(binder, writer);
        renderer.render(points);
        
        final ArgumentCaptor<Collection> aggregatesCaptor = ArgumentCaptor.forClass(Collection.class);
        verify(binder).bind(aggregatesCaptor.capture(), eq(writer));
        assertThat(aggregatesCaptor.getValue()).containsOnly(aggregate);
    }

}
