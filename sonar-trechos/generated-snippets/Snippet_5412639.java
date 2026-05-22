public class Snippet__5412639 {

    public String submit() {
            invocation.getMethodParameters().clear();
            for (ParameterInfo info : methodParameters) {
                invocation.getMethodParameters().add(info.getValue());
            }
            methodParameters.clear();
            methodParameters = null;
            return "ejb-invoker?faces-redirect=true";
        }

}
