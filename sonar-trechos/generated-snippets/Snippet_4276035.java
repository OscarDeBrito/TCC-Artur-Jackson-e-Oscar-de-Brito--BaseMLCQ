public class Snippet__4276035 {

    public Snippet__4276035(final T stepSize, final FieldODEStateAndDerivative<T> reference,
                                   final T[] scaled, final Array2DRowFieldMatrix<T> nordsieck,
                                   final boolean isForward,
                                   final FieldODEStateAndDerivative<T> globalPreviousState,
                                   final FieldODEStateAndDerivative<T> globalCurrentState,
                                   final FieldEquationsMapper<T> equationsMapper) {
            this(stepSize, reference, scaled, nordsieck,
                 isForward, globalPreviousState, globalCurrentState,
                 globalPreviousState, globalCurrentState, equationsMapper);
        }

}
