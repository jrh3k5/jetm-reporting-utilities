package com.google.code.jetm.reporting.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.google.code.jetm.reporting.AggregateBinder;

import etm.core.aggregation.Aggregate;

/**
 * A binder to bind an {@link Aggregate} object to XML and back again.
 *
 * @author jrh3k5
 *
 */

public class XmlAggregateBinder implements AggregateBinder {
    private final Namespace jetmNamespace = Namespace
            .getNamespace("http://code.google.com/p/jetm-reporting-utilities/jetm-measurement");

    /**
     * {@inheritDoc}
     */
    public void bind(Collection<? extends Aggregate> aggregates, Writer writer) {
        final Element rootElement = new Element("measurements", jetmNamespace);
        for (Aggregate aggregate : aggregates)
            rootElement.addContent(toElement(aggregate));

        try {
            new XMLOutputter().output(rootElement, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write aggregate data to XML.", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Aggregate> unbind(Reader reader) {
        Document document;
        try {
            document = new SAXBuilder().build(reader);
        } catch (JDOMException e) {
            throw new RuntimeException("Failed to read XML data from reader.", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read XML data from reader.", e);
        }
        final List<Aggregate> aggregates = new LinkedList<Aggregate>();
        for (Element element : document.getRootElement().getChildren("measurement",
                jetmNamespace))
            aggregates.add(fromElement(element));
        return aggregates;
    }

    /**
     * Turn a {@code <measurement />} XML element into an aggregate.
     *
     * @param element
     *            An {@link Element} representing the XML to be converted.
     * @return An {@link Aggregate} built out of the given XML.
     */
    private Aggregate fromElement(Element element) {
        final String measurementName = element.getChildText("measurementName", jetmNamespace);
        final double min = Double.parseDouble(element.getChildText("min", jetmNamespace));
        final double max = Double.parseDouble(element.getChildText("max", jetmNamespace));
        final double total = Double.parseDouble(element.getChildText("total", jetmNamespace));
        final long measurements = Long.parseLong(element
                .getChildText("measurements", jetmNamespace));

        return new XmlAggregate(min, max, total, measurements, measurementName);
    }

    /**
     * Convert an aggregate to an XML element.
     *
     * @param aggregate
     *            The {@link Aggregate} to be converted into XML.
     * @return An {@link Element} representing the XML built out of the given
     *         aggregate.
     */
    private Element toElement(Aggregate aggregate) {
        final Element element = new Element("measurement", jetmNamespace);

        final Element nameElement = new Element("measurementName", jetmNamespace);
        nameElement.setContent(new CDATA(aggregate.getName()));
        element.addContent(nameElement);

        final Element minElement = new Element("min", jetmNamespace);
        minElement.setText(Double.toString(aggregate.getMin()));
        element.addContent(minElement);

        final Element maxElement = new Element("max", jetmNamespace);
        maxElement.setText(Double.toString(aggregate.getMax()));
        element.addContent(maxElement);

        final Element totalElement = new Element("total", jetmNamespace);
        totalElement.setText(Double.toString(aggregate.getTotal()));
        element.addContent(totalElement);

        final Element measurementsElement = new Element("measurements", jetmNamespace);
        measurementsElement.setText(Long.toString(aggregate.getMeasurements()));
        element.addContent(measurementsElement);

        return element;
    }
}
