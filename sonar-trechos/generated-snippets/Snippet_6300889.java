public class Snippet__6300889 {

    protected void copyHeaders(WebSocketFrame copy)
        {
            finRsvOp = copy.finRsvOp;
            masked = copy.masked;
            mask = null;
            if (copy.mask != null)
                mask = Arrays.copyOf(copy.mask, copy.mask.length);
        }

}
