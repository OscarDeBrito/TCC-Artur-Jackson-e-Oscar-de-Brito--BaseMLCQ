public class Snippet__9517554 {

    @Override
      public void activateApplication(String user, ApplicationId applicationId) {

        this.writeLock.lock();
        try {
          User userDesc = getUser(user);
          if (userDesc != null && userDesc.getActiveApplications() <= 0) {
            return;
          }

          Set<ApplicationId> userApps = usersApplications.get(user);
          if (userApps == null) {
            userApps = new HashSet<ApplicationId>();
            usersApplications.put(user, userApps);
            activeUsers.incrementAndGet();
            metrics.incrActiveUsers();

            // A user is added to active list. Invalidate user-limit cache.
            userLimitNeedsRecompute();
            updateActiveUsersResourceUsage(user);
            LOG.debug("User {} added to activeUsers, currently: {}",
                user, activeUsers);
          }
          if (userApps.add(applicationId)) {
            metrics.activateApp(user);
          }
        } finally {
          this.writeLock.unlock();
        }
      }

}
