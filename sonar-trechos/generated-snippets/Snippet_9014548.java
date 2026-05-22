public class Snippet__9014548 {

    @Override
      public PostingsEnum postings(PostingsEnum reuse, int flags) throws IOException {
        assert !eof;
        //if (DEBUG) {
        //System.out.println("BTTR.docs seg=" + segment);
        //}
        currentFrame.decodeMetaData();
        //if (DEBUG) {
        //System.out.println("  state=" + currentFrame.state);
        //}
        return fr.parent.postingsReader.postings(fr.fieldInfo, currentFrame.state, reuse, flags);
      }

}
