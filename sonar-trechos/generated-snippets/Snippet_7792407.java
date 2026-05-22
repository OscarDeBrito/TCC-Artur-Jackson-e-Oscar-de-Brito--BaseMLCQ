public class CheckGroupPage extends WicketExamplePage
{
	/**
	 * Constructor
	 */
	public CheckGroupPage()
	{
		final CheckGroup<Person> group = new CheckGroup<>("group", new ArrayList<Person>());
		Form<Void> form = new Form<Void>("form")
		{
			@Override
			protected void onSubmit()
			{
				info("selected person(s): " + group.getDefaultModelObjectAsString());
			}
		};

		add(form);
		form.add(group);
		group.add(new CheckGroupSelector("groupselector"));
		ListView<Person> persons = new ListView<Person>("persons",
			ComponentReferenceApplication.getPersons())
		{
			@Override
			protected void populateItem(ListItem<Person> item)
			{
				item.add(new Check<>("checkbox", item.getModel()));
				item.add(new Label("name",
					new PropertyModel<>(item.getDefaultModel(), "name")));
				item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
					"lastName")));
			}

		};

		persons.setReuseItems(true);
		group.add(persons);

		add(new FeedbackPanel("feedback"));
	}

	/**
	 * @see org.apache.wicket.examples.WicketExamplePage#explain()
	 */
	@Override
	protected void explain()
	{
		String html = "<form wicket:id=\"form\">\n" + "<span wicket:id=\"group\">\n"
			+ "<input type=\"checkbox\" wicket:id=\"groupselector\">check/uncheck all</input>\n"
			+ "<tr wicket:id=\"persons\">\n"
			+ "<td><input type=\"checkbox\" wicket:id=\"checkbox\"/></td>\n"
			+ "<td><span wicket:id=\"name\">[this is where name will be]</span></td>\n"
			+ "<td><span wicket:id=\"lastName\">[this is where lastname will be]</span></td>\n"
			+ "</tr>\n</span>\n</form>";
		String code = "&nbsp;&nbsp;&nbsp;&nbsp;Form f=new Form(\"form\");<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;add(f);<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;CheckGroup group=new CheckGroup(\"group\");<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;form.add(group);<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;group.add(new CheckGroupSelector(\"groupselector\"));<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;ListView persons=new ListView(\"persons\", getPersons()) {<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;protected void populateItem(ListItem item) {<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;item.add(new Check(\"check\", item.getModel()));<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;item.add(new Label(\"name\", new PropertyModel(item.getModel(), \"name\")));<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;item.add(new Label(\"lastName\", new PropertyModel(item.getModel(), \"lastName\")));<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;};<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;persons.setReuseItems(true);<br/>"
			+ "&nbsp;&nbsp;&nbsp;&nbsp;group.add(persons);<br/>";
		add(new ExplainPanel(html, code));
	}
}
