public class Snippet__7002266 {

    private Atom getSTTSAtom() {
            Atom atom = new Atom("stts", (byte)0, 0);
            int numAudioFrames = mFrameSize.length - 1;
            atom.setData(new byte[] {
                    0, 0, 0, 0x02,  // entry count
                    0, 0, 0, 0x01,  // first frame contains no audio
                    0, 0, 0, 0,
                    (byte)((numAudioFrames >> 24) & 0xFF), (byte)((numAudioFrames >> 16) & 0xFF),
                    (byte)((numAudioFrames >> 8) & 0xFF), (byte)(numAudioFrames & 0xFF),
                    0, 0, 0x04, 0,  // delay between frames = 1024 samples (cf. timescale = Fs)
            });
            return atom;
        }

}
