public class Snippet__7386739 {

    private void addMethod(Method method) {
    			addMethodIfPossible(method, "get", 0, BeanProperty::addGetter);
    			addMethodIfPossible(method, "is", 0, BeanProperty::addGetter);
    			addMethodIfPossible(method, "set", 1, BeanProperty::addSetter);
    		}

}
