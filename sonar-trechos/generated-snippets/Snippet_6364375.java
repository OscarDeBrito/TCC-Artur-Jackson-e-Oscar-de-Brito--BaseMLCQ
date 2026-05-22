@ApplicationScoped
@Traced
public class TestAnnotatedClass {

    /**
     * Method that we expect to be Traced implicitly.
     */
    public void annotatedClassMethodImplicitlyTraced() {
        System.out.println("Called annotatedClassMethodImplicitlyTraced");
    }

    /**
     * Method that we expect to not be Traced.
     */
    @Traced(value = false)
    public void annotatedClassMethodExplicitlyNotTraced() {
        System.out.println("Called annotatedClassMethodExplicitlyNotTraced");
    }

    /**
     * Method that we expect to be Traced explicitly.
     */
    @Traced(operationName = "explicitOperationName1")
    public void annotatedClassMethodExplicitlyTraced() {
        System.out.println("Called annotatedClassMethodExplicitlyTraced");
    }

    /**
     * Method that we expect to not be Traced.
     */
    @Traced(operationName = "disabledOperationName", value = false)
    public void annotatedClassMethodExplicitlyNotTracedWithOpName() {
        System.out.println("Called annotatedClassMethodExplicitlyNotTracedWithOpName");
    }

    /**
     * Method that we expect to be Traced implicitly and throws an exception.
     */
    public void annotatedClassMethodImplicitlyTracedWithException() {
        System.out.println("Called annotatedClassMethodImplicitlyTracedWithException");
        throw ApplicationUtils.createExampleRuntimeException();
    }
}
