interface Parameters {
    //all have to be repeated, because encoding is not optional,
    //according to the check if (encoding == null) { below (now removed)
    @ParameterDescription(valueName = "charsetName",
        description = "encoding for reading and writing text, if absent the system default is used.")
    Charset getEncoding();

    @ParameterDescription(valueName = "sampleData", description = "data to be used, usually a file name.")
    File getData();

    @ParameterDescription(valueName = "split",
        description = "if true all hyphenated tokens will be separated (default true)")
    @OptionalParameter(defaultValue = "true")
    Boolean getSplitHyphenatedTokens();

    @ParameterDescription(valueName = "language", description = "language which is being processed.")
    String getLang();
  }
