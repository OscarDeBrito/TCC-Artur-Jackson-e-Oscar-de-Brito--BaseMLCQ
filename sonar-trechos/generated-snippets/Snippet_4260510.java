protected class WhenFloatArray extends BaseWhen<float[]>
    {
        public S thenReturn(float... values)
        {
            trainingContext().then(InterceptorUtils.constant(ArrayUtils.clone(values)));
            return self();
        }
    }
