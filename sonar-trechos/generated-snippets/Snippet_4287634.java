public class Snippet__4287634 {

    @Before
      public void setUp() throws Exception {
        Configuration conf = HBaseConfiguration.create(tmpDir.getDefaultConfiguration());
        conf.set(HConstants.TEMPORARY_FS_DIRECTORY_KEY, tmpDir.getFile("hbase-staging").getAbsolutePath());
        hbaseTestUtil = new HBaseTestingUtility(conf);
        hbaseTestUtil.startMiniCluster();
      }

}
