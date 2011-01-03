package com.google.code.jetm.reporting.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import etm.core.aggregation.Aggregate;

/**
 * A binder to bind an {@link Aggregate} object to XML and back again.
 * 
 * @author jrh3k5
 * 
 */

public class AggregateBinder {
    private final Namespace jetmNamespace = Namespace
            .getNamespace("http://code.google.com/p/jetm-reporting-utilities/jetm-measurement");

    /**
     * Marshall a collection of aggregates to XML.
     * 
     * @param aggregates
     *            A {@link Collection} of {@link Aggregate} objects to be
     *            marshalled.
     * @return The marshalled XML.
     */
    public String marshall(List<? extends Aggregate> aggregates) {
        try {
            final Element rootElement = new Element("measurements", jetmNamespace);
            for (Aggregate aggregate : aggregates)
                rootElement.addContent(toElement(aggregate));

            final StringWriter writer = new StringWriter();
            new XMLOutputter().output(rootElement, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("whoops", e);
        }
    }

    /**
     * Unmarshall XML into a list of aggregate objects.
     * 
     * @param xml
     *            The XML to be unmarshalled.
     * @return A {@link List} of {@link Aggregate} objects representing the
     *         unmarshalled XML.
     */
    @SuppressWarnings("unchecked")
    public List<Aggregate> unmarshall(String xml) {
        try {
            final Document document = new SAXBuilder().build(new StringReader(xml));
            final List<Aggregate> aggregates = new LinkedList<Aggregate>();
            for (Element element : (List<Element>) document.getRootElement().getChildren(
                    "measurement", jetmNamespace))
                aggregates.add(fromElement(element));
            return aggregates;
        } catch (Exception e) {
            throw new RuntimeException("whoops", e);
        }
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
