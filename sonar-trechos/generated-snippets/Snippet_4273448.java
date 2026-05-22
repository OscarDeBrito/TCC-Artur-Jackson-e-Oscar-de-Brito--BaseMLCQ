public class MidpointFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {

    /** Simple constructor.
     * Build a midpoint integrator with the given step.
     * @param field field to which the time and state vector elements belong
     * @param step integration step
     */
    public MidpointFieldIntegrator(final Field<T> field, final T step) {
        super(field, "midpoint", step);
    }

    /** {@inheritDoc} */
    @Override
    public T[] getC() {
        final T[] c = MathArrays.buildArray(getField(), 1);
        c[0] = getField().getOne().multiply(0.5);
        return c;
    }

    /** {@inheritDoc} */
    @Override
    public T[][] getA() {
        final T[][] a = MathArrays.buildArray(getField(), 1, 1);
        a[0][0] = fraction(1, 2);
        return a;
    }

    /** {@inheritDoc} */
    @Override
    public T[] getB() {
        final T[] b = MathArrays.buildArray(getField(), 2);
        b[0] = getField().getZero();
        b[1] = getField().getOne();
        return b;
    }

    /** {@inheritDoc} */
    @Override
    protected MidpointFieldStepInterpolator<T>
        createInterpolator(final boolean forward, T[][] yDotK,
                           final FieldODEStateAndDerivative<T> globalPreviousState,
                           final FieldODEStateAndDerivative<T> globalCurrentState,
                           final FieldEquationsMapper<T> mapper) {
        return new MidpointFieldStepInterpolator<>(getField(), forward, yDotK,
                                                    globalPreviousState, globalCurrentState,
                                                    globalPreviousState, globalCurrentState,
                                                    mapper);
    }

}
