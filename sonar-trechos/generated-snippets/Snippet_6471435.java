public class Snippet__6471435 {

    public Snippet__6471435(File inClassFile, byte[] sdeAttr, File outClassFile)
                throws IOException {
                if (!inClassFile.exists()) {
                    throw new FileNotFoundException("no such file: " + inClassFile);
                }

                this.sdeAttr = sdeAttr;
                // get the bytes
                orig = readWhole(inClassFile);
                gen = new byte[orig.length + sdeAttr.length + 100];

                // do it
                addSDE();

                // write result
                FileOutputStream outStream = new FileOutputStream(outClassFile);
                outStream.write(gen, 0, genPos);
                outStream.close();
            }

}
