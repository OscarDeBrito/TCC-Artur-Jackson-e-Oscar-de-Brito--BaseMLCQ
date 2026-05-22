public abstract class InputValidator<T extends JComponent> extends InputVerifier {
    private Border originalBorder;

    @Override
    public final boolean verify(JComponent jComponent) {

        String error = validate((T) jComponent);

        jComponent.setToolTipText(error == null ? "" : error);
        if (originalBorder == null)
            originalBorder = jComponent.getBorder();

        jComponent.setBorder(error == null ? originalBorder : BorderFactory.createLineBorder(JBColor.RED));

        return (error == null);
    }

    @Override
    public final boolean shouldYieldFocus(JComponent jComponent) {
        super.shouldYieldFocus(jComponent);
        return true;
    }

    public abstract String validate(T component);
}
