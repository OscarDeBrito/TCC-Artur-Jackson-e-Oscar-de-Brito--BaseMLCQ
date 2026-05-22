public class Snippet__7231995 {

    @Implementation(minSdk = JELLY_BEAN_MR1)
        @SuppressWarnings("robolectric.ShadowReturnTypeMismatch")
        protected static boolean setLocationProviderEnabledForUser(
            ContentResolver cr, String provider, boolean enabled, int uid) {
          return updateEnabledProviders(cr, provider, enabled);
        }

}
