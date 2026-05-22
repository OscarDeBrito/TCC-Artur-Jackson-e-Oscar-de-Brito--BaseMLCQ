public class Snippet__5657457 {

    @Override
        public MathTransform createMathTransform(final MathTransformFactory factory, final ParameterValueGroup values)
                throws ParameterNotFoundException
        {
            /*
             * The TensorParameters constant used below (WKT1 or EPSG) does not matter,
             * since both of them understand the names of the other TensorParameters.
             */
            return MathTransforms.linear(TensorParameters.WKT1.toMatrix(values));
        }

}
