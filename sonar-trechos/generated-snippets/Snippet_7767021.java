public class Snippet__7767021 {

    @Override
      public ClientResponse<FullResponseHolder> handleChunk(
          ClientResponse<FullResponseHolder> response,
          HttpChunk chunk,
          long chunkNum
      )
      {
        BytesFullResponseHolder holder = (BytesFullResponseHolder) response.getObj();

        if (holder == null) {
          return ClientResponse.finished(null);
        }

        holder.addChunk(getContentBytes(chunk.getContent()));
        return response;
      }

}
