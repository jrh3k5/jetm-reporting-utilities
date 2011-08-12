package com.google.code.jetm.reporting.ext;

import static org.fest.assertions.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * Unit tests for {@link PointFactory}.
 * 
 * @author JH016266
 * 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { PointFactory.class, EtmManager.class, NoOpEtmPoint.class })
public class PointFactoryTest {
    /**
     * Test the creation of a no-op ETM point.
     * 
     * @throws Exception
     *             If any errors occur during the test run.
     */
    @Test
    public void testCreateNoOp() throws Exception {
        final EtmMonitor monitor = mock(EtmMonitor.class);
        mockStatic(EtmManager.class);
        when(EtmManager.getEtmMonitor()).thenReturn(monitor);

        final Class<?> callerClass = Object.class;
        final String methodSig = "a.method.sig";
        final NoOpEtmPoint point = mock(NoOpEtmPoint.class);
        whenNew(NoOpEtmPoint.class).withArguments(callerClass.getCanonicalName() + ": " + methodSig).thenReturn(point);

        assertThat(PointFactory.getPoint(callerClass, methodSig)).isEqualTo(point);
    }

    /**
     * Test the creation of an ETM point from the EtmMonitor.
     * 
     * @throws Exception
     *             If any errors occur during the test run.
     */
    @Test
    public void testCreateRealPoint() throws Exception {
        final EtmMonitor monitor = mock(EtmMonitor.class);
        when(monitor.isStarted()).thenReturn(Boolean.TRUE);

        mockStatic(EtmManager.class);
        when(EtmManager.getEtmMonitor()).thenReturn(monitor);

        final Class<?> callerClass = Object.class;
        final String methodSig = "a.method.sig";
        final EtmPoint point = mock(EtmPoint.class);
        when(monitor.createPoint(callerClass.getCanonicalName() + ": " + methodSig)).thenReturn(point);

        assertThat(PointFactory.getPoint(callerClass, methodSig)).isEqualTo(point);
    }
}
