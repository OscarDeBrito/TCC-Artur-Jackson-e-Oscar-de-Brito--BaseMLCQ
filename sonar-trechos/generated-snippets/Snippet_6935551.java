public class Snippet__6935551 {

    public static ExtensionDescription getDefaultDescription(boolean required,
          boolean repeatable) {
        ExtensionDescription desc =
            ExtensionDescription.getDefaultDescription(GphotoPhotoId.class);
        desc.setRequired(required);
        desc.setRepeatable(repeatable);
        return desc;
      }

}
