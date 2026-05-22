public interface LogCloser {
  long close(AccumuloConfiguration conf, Configuration hadoopConf, VolumeManager fs, Path path)
      throws IOException;
}
