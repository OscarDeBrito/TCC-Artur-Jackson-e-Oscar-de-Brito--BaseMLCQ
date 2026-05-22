public class Snippet__6637469 {

    private static void validateVideo(ShareVideo video, Validator validator) {
            if (video == null) {
                throw new FacebookException("Cannot share a null ShareVideo");
            }

            Uri localUri = video.getLocalUrl();
            if (localUri == null) {
                throw new FacebookException("ShareVideo does not have a LocalUrl specified");
            }

            if (!Utility.isContentUri(localUri) && !Utility.isFileUri(localUri)) {
                throw new FacebookException("ShareVideo must reference a video that is on the device");
            }
        }

}
