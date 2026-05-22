@Command(scope = "jndi", name = "names", description = "List the JNDI names.")
@Service
public class NamesCommand implements Action {

    @Argument(index = 0, name = "context", description = "The JNDI context to display the names", required = false, multiValued = false)
    @Completion(ContextsCompleter.class)
    String context;

    @Reference
    JndiService jndiService;

    @Override
    public Object execute() throws Exception {
        ShellTable table = new ShellTable();

        table.column("JNDI Name");
        table.column("Class Name");

        Map<String, String> names;
        if (context == null) {
            names = jndiService.names();
        } else {
            names = jndiService.names(context);
        }

        for (String name : names.keySet()) {
            table.addRow().addContent(name, names.get(name));
        }

        table.print(System.out);

        return null;
    }

}
