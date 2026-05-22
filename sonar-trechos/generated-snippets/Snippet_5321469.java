public class Snippet__5321469 {

    public int peekUnsignedByte(int offset) throws IOException
        {
            int b = peek(offset);
            if (b < 0)
            {
                throw new EOFException();
            }
            return b;
        }

}
