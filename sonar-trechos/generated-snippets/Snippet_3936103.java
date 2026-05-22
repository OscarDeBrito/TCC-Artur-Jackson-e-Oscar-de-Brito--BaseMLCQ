public class Snippet__3936103 {

    public static abstract class AbstractFixture implements Fixture {
        private ZipFixture parent;
    
        protected AbstractFixture(ZipFixture parent) {
          this.parent = parent;
        }
    
        /**
         * Ends the current flow target and returns the parent flow target. For example, in the
         * following code snippet the <code>end</code> after <code>.version("2.0.0")</code> marks
         * the end of the manifest. Commands after that relate to the parent jar file of the manifest.
         * 
         * <code>
         * ArchiveFixtures.ZipFixture zip = ArchiveFixtures.newZip()
         *   .jar("test.jar")
         *     .manifest()
         *       .symbolicName("com.ibm.test")
         *       .version("2.0.0")
         *     .end()
         *     .file("random.txt", "Some text")
         *   .end();
         * </code>
         * @return
         */
        public ZipFixture end() {
          return (parent == null) ? (ZipFixture) this : parent;
        }
      }

}
