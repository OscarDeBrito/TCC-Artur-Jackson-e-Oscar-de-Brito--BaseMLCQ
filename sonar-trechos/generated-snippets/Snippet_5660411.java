public class Snippet__5660411 {

    @Override
        public CartesianCS createCartesianCS(final Map<String,?> properties,
                final CoordinateSystemAxis axis0,
                final CoordinateSystemAxis axis1,
                final CoordinateSystemAxis axis2) throws FactoryException
        {
            final DefaultCartesianCS cs;
            try {
                cs = new DefaultCartesianCS(complete(properties), axis0, axis1, axis2);
            } catch (IllegalArgumentException exception) {
                throw new InvalidGeodeticParameterException(exception);
            }
            return unique("createCartesianCS", cs);
        }

}
