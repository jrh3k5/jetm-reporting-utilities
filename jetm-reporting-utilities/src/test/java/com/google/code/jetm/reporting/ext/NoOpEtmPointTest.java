package com.google.code.jetm.reporting.ext;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link NoOpEtmPoint}.
 * 
 * @author JH016266
 * 
 */

public class NoOpEtmPointTest {
    private final String name = "a.point.name";
    private NoOpEtmPoint point;

    /**
     * Set up the point for each test.
     */
    @Before
    public void setUp() {
        point = new NoOpEtmPoint(name);
    }

    /**
     * Test the altering of the name.
     */
    @Test
    public void testAlterName() {
        final String newName = "AXJFKDLS:FSD" + name;
        point.alterName(newName);
        assertThat(point.getName()).isEqualTo(newName);
    }

    /**
     * Test the retrieval of the name.
     */
    @Test
    public void testGetName() {
        assertThat(point.getName()).isEqualTo(name);
    }

}
