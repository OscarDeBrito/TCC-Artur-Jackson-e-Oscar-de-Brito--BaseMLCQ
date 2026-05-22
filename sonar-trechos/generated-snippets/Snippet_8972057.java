public class Snippet__8972057 {

    @Override
      public void deleteDocByQuery(String query) {
        if(docBuilder != null){
          docBuilder.writer.deleteByQuery(query);
        } 
      }

}
