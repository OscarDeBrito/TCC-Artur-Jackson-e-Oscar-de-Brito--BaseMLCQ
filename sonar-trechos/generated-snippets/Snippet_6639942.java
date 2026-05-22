public class Snippet__6639942 {

    final public class ProfileManager {
        public static final String ACTION_CURRENT_PROFILE_CHANGED =
                "com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED";
        public static final String EXTRA_OLD_PROFILE =
                "com.facebook.sdk.EXTRA_OLD_PROFILE";
        public static final String EXTRA_NEW_PROFILE =
                "com.facebook.sdk.EXTRA_NEW_PROFILE";

        private static volatile ProfileManager instance;

        private final LocalBroadcastManager localBroadcastManager;
        private final ProfileCache profileCache;
        private Profile currentProfile;


        ProfileManager(
                LocalBroadcastManager localBroadcastManager,
                ProfileCache profileCache) {
            Validate.notNull(localBroadcastManager, "localBroadcastManager");
            Validate.notNull(profileCache, "profileCache");
            this.localBroadcastManager = localBroadcastManager;
            this.profileCache = profileCache;
        }

        static ProfileManager getInstance() {
            if (instance == null) {
                synchronized (ProfileManager.class) {
                    if (instance == null) {
                        Context applicationContext = FacebookSdk.getApplicationContext();
                        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(
                                applicationContext);

                        instance = new ProfileManager(localBroadcastManager, new ProfileCache());
                    }
                }
            }
            return instance;
        }

        Profile getCurrentProfile() {
            return currentProfile;
        }

        boolean loadCurrentProfile() {
            Profile profile = profileCache.load();

            if (profile != null) {
                setCurrentProfile(profile, false);
                return true;
            }

            return false;
        }

        void setCurrentProfile(@Nullable Profile currentProfile) {
            setCurrentProfile(currentProfile, true);
        }

        private void setCurrentProfile(@Nullable Profile currentProfile, boolean writeToCache) {
            Profile oldProfile = this.currentProfile;
            this.currentProfile = currentProfile;

            if (writeToCache) {
                if (currentProfile != null) {
                    profileCache.save(currentProfile);
                } else {
                    profileCache.clear();
                }
            }

            if (!Utility.areObjectsEqual(oldProfile, currentProfile)) {
                sendCurrentProfileChangedBroadcast(oldProfile, currentProfile);
            }
        }

        private void sendCurrentProfileChangedBroadcast(
                Profile oldProfile,
                Profile currentProfile) {
            Intent intent = new Intent(ACTION_CURRENT_PROFILE_CHANGED);

            intent.putExtra(EXTRA_OLD_PROFILE, oldProfile);
            intent.putExtra(EXTRA_NEW_PROFILE, currentProfile);

            localBroadcastManager.sendBroadcast(intent);
        }
    }

}
