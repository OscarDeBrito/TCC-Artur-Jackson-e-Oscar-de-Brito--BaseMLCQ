public class Snippet__5497900 {

    public QmfEvent createUnsubscribeEvent()
        {
            QmfEvent unsubscribe = new QmfEvent(_unsubscribeSchema);
            unsubscribe.setSeverity("info");
            unsubscribe.setValue("dest", getStringValue("name"));
            // TODO Not sure of a way to get these for Java Broker Subscription.
            //unsubscribe.setValue("rhost", _connection.getName());
            //unsubscribe.setValue("user", getStringValue("authIdentity"));
            return unsubscribe;
        }

}
