public static class Builder {

      /**
       * @see AssumeRoleOptions#externalId
       */
      public static AssumeRoleOptions externalId(String externalId) {
         return new AssumeRoleOptions().externalId(externalId);
      }

      /**
       * @see AssumeRoleOptions#durationSeconds
       */
      public static AssumeRoleOptions durationSeconds(long durationSeconds) {
         return new AssumeRoleOptions().durationSeconds(durationSeconds);
      }

      /**
       * @see AssumeRoleOptions#policy
       */
      public static AssumeRoleOptions policy(String policy) {
         return new AssumeRoleOptions().policy(policy);
      }
   }
