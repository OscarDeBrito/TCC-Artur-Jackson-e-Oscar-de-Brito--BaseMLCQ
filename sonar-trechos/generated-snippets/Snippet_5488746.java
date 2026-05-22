public class Snippet__5488746 {

    @Override
        public Content getMessageContent(final long messageId, final long limit, boolean returnJson, boolean decompressBeforeLimiting)
        {
            final MessageContentFinder messageFinder = new MessageContentFinder(messageId);
            visit(messageFinder);
            if (messageFinder.isFound())
            {
                return createMessageContent(messageFinder.getMessageReference(), returnJson, limit, decompressBeforeLimiting);
            }
            else
            {
                return null;
            }
        }

}
