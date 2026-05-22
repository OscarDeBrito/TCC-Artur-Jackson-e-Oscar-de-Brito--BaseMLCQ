public class Snippet__7273237 {

    public Snippet__7273237(JavaClass javaClass, boolean retained) {
                allInstancesSize = javaClass.getAllInstancesSize();
                isArray = javaClass.isArray();
                instanceSize = javaClass.getInstanceSize();
                instancesCount = javaClass.getInstancesCount();
                retainedSizeByClass = retained ? javaClass.getRetainedSizeByClass() : DataType.RETAINED_SIZE.getNotAvailableValue();
                javaClassId = javaClass.getJavaClassId();
                name = javaClass.getName();
            }

}
