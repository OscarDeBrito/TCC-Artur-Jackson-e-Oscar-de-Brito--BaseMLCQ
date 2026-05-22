public class Snippet__4602360 {

    @Override
      public void writeRequest(DataOutput output) throws IOException {
        output.writeInt(partitionId);
        output.writeInt(vertexIdMutations.size());
        for (Entry<I, VertexMutations<I, V, E>> entry :
            vertexIdMutations.entrySet()) {
          entry.getKey().write(output);
          entry.getValue().write(output);
        }
      }

}
