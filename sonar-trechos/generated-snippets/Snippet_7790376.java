public abstract class AjaxButton extends Button
{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AjaxButton.class);

	private final Form<?> form;

	/**
	 * Construct.
	 * 
	 * @param id
	 */
	public AjaxButton(String id)
	{
		this(id, null, null);
	}

	/**
	 * 
	 * Construct.
	 * 
	 * @param id
	 * @param model
	 *            model used to set <code>value</code> markup attribute
	 */
	public AjaxButton(String id, IModel<String> model)
	{
		this(id, model, null);
	}

	/**
	 * 
	 * Construct.
	 * 
	 * @param id
	 * @param form
	 */
	public AjaxButton(String id, Form<?> form)
	{
		this(id, null, form);
	}


	/**
	 * Construct.
	 * 
	 * @param id
	 * @param model
	 *            model used to set <code>value</code> markup attribute
	 * @param form
	 */
	public AjaxButton(String id, IModel<String> model, final Form<?> form)
	{
		super(id, model);
		this.form = form;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();

		add(newAjaxFormSubmitBehavior("click"));
	}

	protected AjaxFormSubmitBehavior newAjaxFormSubmitBehavior(String event)
	{
		return new AjaxFormSubmitBehavior(form, event)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target)
			{
				AjaxButton.this.onSubmit(target);
			}

			@Override
			protected void onAfterSubmit(AjaxRequestTarget target)
			{
				AjaxButton.this.onAfterSubmit(target);
			}

			@Override
			protected void onError(AjaxRequestTarget target)
			{
				AjaxButton.this.onError(target);
			}

			@Override
			protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
			{
				super.updateAjaxAttributes(attributes);

				// do not allow normal form submit to happen
				attributes.setPreventDefault(true);

				AjaxButton.this.updateAjaxAttributes(attributes);
			}

			@Override
			public boolean getDefaultProcessing()
			{
				return AjaxButton.this.getDefaultFormProcessing();
			}
			
			@Override
			public boolean getStatelessHint(Component component)
			{
				return AjaxButton.this.getStatelessHint();
			}
		};
	}

	protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
	{
	}

	/**
	 * Returns the form if it was set in constructor, otherwise returns the form nearest in parent
	 * hierarchy.
	 * 
	 * @see org.apache.wicket.markup.html.form.FormComponent#getForm()
	 */
	@Override
	public Form<?> getForm()
	{
		if (form != null)
		{
			return form;
		}
		else
		{
			return super.getForm();
		}
	}

	/**
	 * This method is never called.
	 * 
	 * @see #onSubmit(AjaxRequestTarget)
	 */
	@Override
	public final void onSubmit()
	{
		logger.warn("unexpected invocation of #onSubmit() on {}", this);
	}

	@Override
	public final void onAfterSubmit()
	{
		logger.warn("unexpected invocation of #onAfterSubmit() on {}", this);
	}

	/**
	 * This method is never called.
	 * 
	 * @see #onError(AjaxRequestTarget)
	 */
	@Override
	public final void onError()
	{
		logger.warn("unexpected invocation of #onError() on {}", this);
	}

	/**
	 * Listener method invoked on form submit with no errors, before {@link Form#onSubmit()}.
	 * 
	 * @param target
	 */
	protected void onSubmit(AjaxRequestTarget target)
	{
	}

	/**
	 * Listener method invoked on form submit with no errors, after {@link Form#onSubmit()}.
	 *
	 * @param target
	 */
	protected void onAfterSubmit(AjaxRequestTarget target)
	{
	}

	/**
	 * Listener method invoked on form submit with errors
	 *
	 * @param target
	 */
	protected void onError(AjaxRequestTarget target)
	{
	}

	@Override
	protected boolean getStatelessHint()
	{
		return false;
	}
}
