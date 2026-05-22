public class Snippet__4906479 {

    @Override
            public int nextDoc() throws IOException {
                if (docNo == NO_MORE_DOCS) {
                    return docNo;
                }

                if (firstTime) {
                    firstTime = false;
                    // get first doc of context scorer
                    int docId = contextScorer.nextDoc();
                    if (docId != NO_MORE_DOCS) {
                        contextNo = docId;
                    }
                }
                // move to next candidate
                do {
                    docNo++;
                } while (reader.isDeleted(docNo) && docNo < reader.maxDoc());

                // check with contextScorer
                while (contextNo != -1 && contextNo == docNo) {
                    docNo++;
                    int docId = contextScorer.nextDoc();
                    contextNo = docId == NO_MORE_DOCS ? -1 : docId;
                }
                if (docNo >= reader.maxDoc()) {
                    docNo = NO_MORE_DOCS;
                }
                return docNo;
            }

}
