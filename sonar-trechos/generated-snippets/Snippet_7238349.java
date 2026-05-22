public class Snippet__7238349 {

    @Override
      public void finishActivity(Activity activity) {
        checkNotNull(controller);
        checkState(controller.get() == activity);
        activity.finish();
        Stage stage = ActivityLifecycleMonitorRegistry.getInstance().getLifecycleStageOf(activity);
        switch (stage) {
          case RESUMED:
            controller.pause().stop().destroy();
            return;
          case PAUSED:
            controller.stop().destroy();
            return;
          case STOPPED:
            controller.destroy();
            return;
          default:
            throw new IllegalStateException(
                String.format(
                    "Activity's stage must be RESUMED, PAUSED or STOPPED but was %s.", stage));
        }
      }

}
