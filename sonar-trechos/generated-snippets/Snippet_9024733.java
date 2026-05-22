public class Snippet__9024733 {

    protected void doDeleteById(DeleteUpdateCommand cmd) throws IOException {

        setupRequest(cmd);

        boolean dropCmd = false;
        if (!forwardToLeader) {
          dropCmd  = versionDelete(cmd);
        }

        if (dropCmd) {
          // TODO: do we need to add anything to the response?
          return;
        }

        doDistribDeleteById(cmd);

        // cmd.getIndexId == null when delete by query
        // TODO: what to do when no idField?
        if (returnVersions && rsp != null && cmd.getIndexedId() != null && idField != null) {
          if (deleteResponse == null) {
            deleteResponse = new NamedList<>(1);
            rsp.add("deletes",deleteResponse);
          }
          if (scratch == null) scratch = new CharsRefBuilder();
          idField.getType().indexedToReadable(cmd.getIndexedId(), scratch);
          deleteResponse.add(scratch.toString(), cmd.getVersion());  // we're returning the version of the delete.. not the version of the doc we deleted.
        }
      }

}
