public class Snippet__8657644 {

    @Override public void writeExternal(ObjectOutput out) throws IOException {
            jobId.writeExternal(out);
            out.writeLong(msgId);
            out.writeInt(reducer);
            out.writeInt(off);
            U.writeByteArray(out, buf);
        }

}
