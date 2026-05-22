public class Snippet__7248409 {

    protected void notifyCannotPerform() {
            DialogDisplayer.getDefault().notifyLater(new NotifyDescriptor.Message(
                    NbBundle.getMessage(DataSourceAction.class,
                    "MSG_Cannot_perform_action_in_this_context"), // NOI18N
                    NotifyDescriptor.ERROR_MESSAGE));
        }

}
