public class NotificationHubListener implements NotificationListener {
    /**
     * MBean for which this listener is added
     */
    private ObjectName name;

    /**
     * Counter to indicate how many listener are attached to this MBean
     */
    private int numCounter = 0;


    protected NotificationHubListener(ObjectName name) {
      this.name = name;
    }

    public int incNumCounter() {
      return ++numCounter;
    }

    public int decNumCounter() {
      return --numCounter;
    }

    public int getNumCounter() {
      return this.numCounter;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
      NotificationKey key = new NotificationKey(name);
      notification.setUserData(memberSource);
      repo.putEntryInLocalNotificationRegion(key, notification);
    }

  }
