public class RadioDirective extends AbstractDirective {
    public String getBeanName() {
        return "radio";
    }

    protected Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new Radio(stack, req, res);
    }
}
