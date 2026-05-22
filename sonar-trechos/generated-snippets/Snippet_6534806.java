public class Snippet__6534806 {

    public Snippet__6534806(int zone, int volume, int min, int max) {
            super("setAudioVolume", "1.1");
            long scaled_volume = scaleVolume(volume, min, max);
            params = new Param[] { new Param(scaled_volume, zone) };
        }

}
