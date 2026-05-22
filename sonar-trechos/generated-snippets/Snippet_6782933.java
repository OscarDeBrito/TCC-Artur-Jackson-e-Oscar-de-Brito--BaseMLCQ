public class Snippet__6782933 {

    private static InputStream inputStreamFromJar(URL resourceUrl)
          throws URISyntaxException, IOException {
        // Jar URLs look like this: jar:file:/path/to/file.jar!/entry/within/jar
        // So take apart the URL to open the jar /path/to/file.jar and read the entry
        // entry/within/jar from it.
        String resourceUrlString = resourceUrl.toString().substring("jar:".length());
        int bang = resourceUrlString.lastIndexOf('!');
        String entryName = resourceUrlString.substring(bang + 1);
        if (entryName.startsWith("/")) {
          entryName = entryName.substring(1);
        }
        URI jarUri = new URI(resourceUrlString.substring(0, bang));
        JarFile jar = new JarFile(new File(jarUri));
        JarEntry entry = jar.getJarEntry(entryName);
        InputStream in = jar.getInputStream(entry);
        // We have to be careful not to close the JarFile before the stream has been read, because
        // that would also close the stream. So we defer closing the JarFile until the stream is closed.
        return new FilterInputStream(in) {
          @Override
          public void close() throws IOException {
            super.close();
            jar.close();
          }
        };
      }

}
