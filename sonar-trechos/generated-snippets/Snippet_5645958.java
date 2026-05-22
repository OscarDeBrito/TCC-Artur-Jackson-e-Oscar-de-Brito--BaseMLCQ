public class GM_Object extends XmlAdapter<GM_Object, Geometry> {
    /**
     * The Geometry value covered by a {@code gml:**} element.
     */
    @XmlElementRef(name = "AbstractGeometry", namespace = Namespaces.GML, type = JAXBElement.class)
    protected JAXBElement<? extends Geometry> geometry;

    /**
     * Empty constructor for JAXB and subclasses only.
     */
    public GM_Object() {
    }

    /**
     * Converts an adapter read from an XML stream to the GeoAPI interface which will
     * contains this value. JAXB calls automatically this method at unmarshalling time.
     *
     * @param  value  the adapter for a geometry value.
     * @return an instance of the GeoAPI interface which represents the geometry value.
     */
    @Override
    public final Geometry unmarshal(final GM_Object value) {
        if (value != null) {
            final JAXBElement<? extends Geometry> g = value.geometry;
            if (g != null) {
                return g.getValue();
            }
        }
        return null;
    }

    /**
     * Converts a GeoAPI interface to the appropriate adapter for the way it will be
     * marshalled into an XML file or stream. JAXB calls automatically this method at
     * marshalling time.
     *
     * @param  value  the geometry value, here the interface.
     * @return the adapter for the given value.
     */
    @Override
    public final GM_Object marshal(final Geometry value) {
        if (value == null) {
            return null;
        }
        return wrap(value);
    }

    /**
     * Returns the geometry value to be covered by a {@code gml:**} element.
     * The default implementation returns {@code null} if all cases. Subclasses
     * must override this method in order to provide useful marshalling.
     *
     * @param  value  the value to marshal.
     * @return the adapter which covers the geometry value.
     */
    protected GM_Object wrap(Geometry value) {
        return null;
    }
}
