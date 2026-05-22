public class Snippet__7044394 {

    public CaptureDeviceInput activateInput(CaptureDeviceInput activateMe) throws EncodingException
      {
        // NOTE: This was removed so we always set the input before we start capture. There was a bug where the audio was
        // getting cut out of some recordings due to the audio standard not being set correctly. This will hopefully
        // resolve that.
        // if (activeSource == activateMe) return activeSource;
        super.activateInput(activateMe);
        if (activeSource != null && isLoaded())
        {
          boolean savePrefsAfter = (activeSource.getBrightness() < 0) || (activeSource.getContrast() < 0) ||
              (activeSource.getHue() < 0) || (activeSource.getSaturation() < 0) || (activeSource.getSharpness() < 0);

          synchronized (devlock)
          {
            setChannel0(pHandle, Integer.toString(activeSource.getIndex()));
          }
          int[] defaultColors = updateColors();
          activeSource.setDefaultColors(defaultColors[0], defaultColors[1], defaultColors[2], defaultColors[3],
              defaultColors[4]);

          if (savePrefsAfter)
            writePrefs();
        }
        return activeSource;
      }

}
