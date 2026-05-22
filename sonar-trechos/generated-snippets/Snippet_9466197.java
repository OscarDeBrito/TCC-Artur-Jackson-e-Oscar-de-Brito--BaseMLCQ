public class Snippet__9466197 {

    @Override
      public int compareInputOrderTo(SchedulableEntity other) {
        if (other instanceof SchedulerApplicationAttempt) {
          return getApplicationId().compareTo(
            ((SchedulerApplicationAttempt)other).getApplicationId());
        }
        return 1;//let other types go before this, if any
      }

}
