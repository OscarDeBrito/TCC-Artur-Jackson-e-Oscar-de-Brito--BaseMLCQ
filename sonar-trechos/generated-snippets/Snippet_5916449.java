private final class ExceptionXmlErrorListener extends AbstractCollection
    {
        public boolean add(Object o)
        {
            assert ValidatingXMLInputStream.this._exception == null;
            
            ValidatingXMLInputStream.this._exception = 
                new XMLStreamValidationException( (XmlError)o );

            return false;
        }

        public Iterator iterator()
        {
            return Collections.EMPTY_LIST.iterator();
        }

        public int size()
        {
            return 0;
        }
    }
