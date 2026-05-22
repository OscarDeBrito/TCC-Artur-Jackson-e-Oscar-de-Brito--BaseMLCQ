public class Snippet__6999266 {

    public static File gunzip(File src, File dest) {

        byte[] buffer = new byte[1024];

        try{

          GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(src));

          FileOutputStream out = new FileOutputStream(dest);

          int len;
          while ((len = gzis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
          }

          gzis.close();
          out.close();

        } catch(IOException ex){
           ex.printStackTrace();
        }
        return dest;
       }

}
