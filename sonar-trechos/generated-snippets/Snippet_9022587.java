public class Snippet__9022587 {

    void doAdd(int line, String[] vals, SolrInputDocument doc, AddUpdateCommand template) throws IOException {
        // the line number is passed for error reporting in MT mode as well as for optional rowId.
        // first, create the lucene document
        for (int i=0; i<vals.length; i++) {
          if (adders[i]==null) continue;  // skip this field
          String val = vals[i];
          adders[i].add(doc, line, i, val);
        }

        // add any literals
        for (String fname : literals.keySet()) {
          String val = literals.get(fname);
          doc.addField(fname, val);
        }
        if (rowId != null){
          doc.addField(rowId, line + rowIdOffset);
        }
        template.solrDoc = doc;
        processor.processAdd(template);
      }

}
