@Bean(typeName="output")
public class Output extends HtmlElementMixed {

	/**
	 * {@doc HTML5.forms#attr-output-for for} attribute.
	 *
	 * <p>
	 * Specifies controls from which the output was calculated.
	 *
	 * @param _for The new value for this attribute.
	 * @return This object (for method chaining).
	 */
	public final Output _for(String _for) {
		attr("for", _for);
		return this;
	}

	/**
	 * {@doc HTML5.forms#attr-fae-form form} attribute.
	 *
	 * <p>
	 * Associates the control with a form element.
	 *
	 * @param form The new value for this attribute.
	 * @return This object (for method chaining).
	 */
	public final Output form(String form) {
		attr("form", form);
		return this;
	}

	/**
	 * {@doc HTML5.forms#attr-fe-name name} attribute.
	 *
	 * <p>
	 * Name of form control to use for form submission and in the form.elements API.
	 *
	 * @param name The new value for this attribute.
	 * @return This object (for method chaining).
	 */
	public final Output name(String name) {
		attr("name", name);
		return this;
	}


	//-----------------------------------------------------------------------------------------------------------------
	// Overridden methods
	//-----------------------------------------------------------------------------------------------------------------

	@Override /* HtmlElement */
	public final Output _class(String _class) {
		super._class(_class);
		return this;
	}

	@Override /* HtmlElement */
	public final Output id(String id) {
		super.id(id);
		return this;
	}

	@Override /* HtmlElement */
	public final Output style(String style) {
		super.style(style);
		return this;
	}

	@Override /* HtmlElementMixed */
	public Output children(Object...children) {
		super.children(children);
		return this;
	}

	@Override /* HtmlElementMixed */
	public Output child(Object child) {
		super.child(child);
		return this;
	}
}
