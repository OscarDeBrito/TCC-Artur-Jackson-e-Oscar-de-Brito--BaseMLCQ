class FileCreator {
  private static final Pattern CREATE_FILE_PATTERN = compile("// Create file (.*)");

  File createFileFrom(String comment) {
    String fileName = null;
    try (Scanner scanner = new Scanner(comment)) {
	    while (scanner.hasNextLine()) {
	      String line = scanner.nextLine();
	      Matcher matcher = CREATE_FILE_PATTERN.matcher(line);
	      if (!matcher.matches()) {
	        return null;
	      }
	      fileName = matcher.group(1);
	      break;
	    }
    }
    return createFile(fileName, comment);
  }

  private File createFile(String fileName, String contents) {
    ensureParentDirectoryExists();
    File file = protoFile(fileName);
    if (file.isFile()) {
      file.delete();
    }
    Writer out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(file));
      out.write(contents);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeQuietly(out);
    }
    return file;
  }

  private void closeQuietly(Writer out) {
    if (out == null) {
      return;
    }
    try {
      out.close();
    } catch (IOException e) {}
  }
}
