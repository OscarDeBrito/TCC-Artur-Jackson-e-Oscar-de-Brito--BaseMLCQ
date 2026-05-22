@SuppressWarnings({
    "StringEquality"
})
public class InitMethod$JAXB
    extends JAXBObject<InitMethod> {


    public InitMethod$JAXB() {
        super(InitMethod.class, null, new QName("http://java.sun.com/xml/ns/javaee".intern(), "init-methodType".intern()), NamedMethod$JAXB.class);
    }

    public static InitMethod readInitMethod(final XoXMLStreamReader reader, final RuntimeContext context)
        throws Exception {
        return _read(reader, context);
    }

    public static void writeInitMethod(final XoXMLStreamWriter writer, final InitMethod initMethod, final RuntimeContext context)
        throws Exception {
        _write(writer, initMethod, context);
    }

    public void write(final XoXMLStreamWriter writer, final InitMethod initMethod, final RuntimeContext context)
        throws Exception {
        _write(writer, initMethod, context);
    }

    public final static InitMethod _read(final XoXMLStreamReader reader, RuntimeContext context)
        throws Exception {

        // Check for xsi:nil
        if (reader.isXsiNil()) {
            return null;
        }

        if (context == null) {
            context = new RuntimeContext();
        }

        final InitMethod initMethod = new InitMethod();
        context.beforeUnmarshal(initMethod, LifecycleCallback.NONE);


        // Check xsi:type
        final QName xsiType = reader.getXsiType();
        if (xsiType != null) {
            if (("init-methodType" != xsiType.getLocalPart()) || ("http://java.sun.com/xml/ns/javaee" != xsiType.getNamespaceURI())) {
                return context.unexpectedXsiType(reader, InitMethod.class);
            }
        }

        // Read attributes
        for (final Attribute attribute : reader.getAttributes()) {
            if (("id" == attribute.getLocalName()) && (("" == attribute.getNamespace()) || (attribute.getNamespace() == null))) {
                // ATTRIBUTE: id
                final String id = Adapters.collapsedStringAdapterAdapter.unmarshal(attribute.getValue());
                context.addXmlId(reader, id, initMethod);
                initMethod.id = id;
            } else if (XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI != attribute.getNamespace()) {
                context.unexpectedAttribute(attribute, new QName("", "id"));
            }
        }

        // Read elements
        for (final XoXMLStreamReader elementReader : reader.getChildElements()) {
            if (("create-method" == elementReader.getLocalName()) && ("http://java.sun.com/xml/ns/javaee" == elementReader.getNamespaceURI())) {
                // ELEMENT: createMethod
                final NamedMethod createMethod = readNamedMethod(elementReader, context);
                initMethod.createMethod = createMethod;
            } else if (("bean-method" == elementReader.getLocalName()) && ("http://java.sun.com/xml/ns/javaee" == elementReader.getNamespaceURI())) {
                // ELEMENT: beanMethod
                final NamedMethod beanMethod = readNamedMethod(elementReader, context);
                initMethod.beanMethod = beanMethod;
            } else {
                context.unexpectedElement(elementReader, new QName("http://java.sun.com/xml/ns/javaee", "create-method"), new QName("http://java.sun.com/xml/ns/javaee", "bean-method"));
            }
        }

        context.afterUnmarshal(initMethod, LifecycleCallback.NONE);

        return initMethod;
    }

    public final InitMethod read(final XoXMLStreamReader reader, final RuntimeContext context)
        throws Exception {
        return _read(reader, context);
    }

    public final static void _write(final XoXMLStreamWriter writer, final InitMethod initMethod, RuntimeContext context)
        throws Exception {
        if (initMethod == null) {
            writer.writeXsiNil();
            return;
        }

        if (context == null) {
            context = new RuntimeContext();
        }

        final String prefix = writer.getUniquePrefix("http://java.sun.com/xml/ns/javaee");
        if (InitMethod.class != initMethod.getClass()) {
            context.unexpectedSubclass(writer, initMethod, InitMethod.class);
            return;
        }

        context.beforeMarshal(initMethod, LifecycleCallback.NONE);


        // ATTRIBUTE: id
        final String idRaw = initMethod.id;
        if (idRaw != null) {
            String id = null;
            try {
                id = Adapters.collapsedStringAdapterAdapter.marshal(idRaw);
            } catch (final Exception e) {
                context.xmlAdapterError(initMethod, "id", CollapsedStringAdapter.class, String.class, String.class, e);
            }
            writer.writeAttribute("", "", "id", id);
        }

        // ELEMENT: createMethod
        final NamedMethod createMethod = initMethod.createMethod;
        if (createMethod != null) {
            writer.writeStartElement(prefix, "create-method", "http://java.sun.com/xml/ns/javaee");
            writeNamedMethod(writer, createMethod, context);
            writer.writeEndElement();
        } else {
            context.unexpectedNullValue(initMethod, "createMethod");
        }

        // ELEMENT: beanMethod
        final NamedMethod beanMethod = initMethod.beanMethod;
        if (beanMethod != null) {
            writer.writeStartElement(prefix, "bean-method", "http://java.sun.com/xml/ns/javaee");
            writeNamedMethod(writer, beanMethod, context);
            writer.writeEndElement();
        } else {
            context.unexpectedNullValue(initMethod, "beanMethod");
        }

        context.afterMarshal(initMethod, LifecycleCallback.NONE);
    }

}
