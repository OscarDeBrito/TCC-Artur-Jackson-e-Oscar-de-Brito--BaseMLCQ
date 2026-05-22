public class Snippet__5738914 {

    private void handleControlMessage(ChannelHandlerContext ctx, ControlMessage controlMessage) throws Exception {
            Channel channel = ctx.channel();
            KerberosSaslNettyClient saslNettyClient = getChannelSaslClient(channel);
            if (controlMessage == ControlMessage.SASL_COMPLETE_REQUEST) {
                LOG.debug("Server has sent us the SaslComplete message. Allowing normal work to proceed.");

                if (!saslNettyClient.isComplete()) {
                    String errorMessage =
                        "Server returned a Sasl-complete message, but as far as we can tell, we are not authenticated yet.";
                    LOG.error(errorMessage);
                    throw new Exception(errorMessage);
                }
                ctx.pipeline().remove(this);
                this.client.channelReady(channel);

                // We call fireChannelRead since the client is allowed to
                // perform this request. The client's request will now proceed
                // to the next pipeline component namely StormClientHandler.
                ctx.fireChannelRead(controlMessage);
            } else {
                LOG.warn("Unexpected control message: {}", controlMessage);
            }
        }

}
