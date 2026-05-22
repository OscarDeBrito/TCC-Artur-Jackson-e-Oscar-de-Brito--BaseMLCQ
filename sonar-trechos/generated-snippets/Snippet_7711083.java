public class Snippet__7711083 {

    public final @NotNull Builder setHeader(@NotNull String header) {
                this.setArg2(Unpooled.wrappedBuffer(header.getBytes(StandardCharsets.UTF_8)));
                this.header = header;
                return this;
            }

}
