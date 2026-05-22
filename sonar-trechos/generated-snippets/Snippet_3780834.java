public class Snippet__3780834 {

    @Override
      public final void writeToStream(DataOutputStream output) throws IOException {
        output.writeUTF(uri.toString());
        output.writeBoolean(isRemoveAction);
        output.writeInt(data.length);
        output.write(data);
        output.writeInt(keys.size());
        for (int i = 0; i < keys.size(); i++) {
          writeKey(output, keys.get(i));
        }
      }

}
