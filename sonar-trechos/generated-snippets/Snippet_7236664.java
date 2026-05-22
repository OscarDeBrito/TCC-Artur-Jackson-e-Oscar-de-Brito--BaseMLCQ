public class Snippet__7236664 {

    private ShadowInfo checkShadowPickers(String instrumentedClassName, Class<?> clazz) {
        String shadowPickerClassName = shadowPickers.get(instrumentedClassName);
        if (shadowPickerClassName == null) {
          return null;
        }

        ClassLoader classLoader = clazz.getClassLoader();
        try {
          Class<? extends ShadowPicker<?>> shadowPickerClass =
              (Class<? extends ShadowPicker<?>>) classLoader.loadClass(shadowPickerClassName);
          ShadowPicker<?> shadowPicker = shadowPickerClass.getDeclaredConstructor().newInstance();
          Class<?> selectedShadowClass = shadowPicker.pickShadowClass();
          if (selectedShadowClass == null) {
            return obtainShadowInfo(Object.class, true);
          }
          ShadowInfo shadowInfo = obtainShadowInfo(selectedShadowClass);

          if (!shadowInfo.shadowedClassName.equals(instrumentedClassName)) {
            throw new IllegalArgumentException("Implemented class for "
                + selectedShadowClass.getName() + " (" + shadowInfo.shadowedClassName + ") != "
                + instrumentedClassName);
          }

          return shadowInfo;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
            | IllegalAccessException | InstantiationException e) {
          throw new RuntimeException("Failed to resolve shadow picker for " + instrumentedClassName,
              e);
        }
      }

}
