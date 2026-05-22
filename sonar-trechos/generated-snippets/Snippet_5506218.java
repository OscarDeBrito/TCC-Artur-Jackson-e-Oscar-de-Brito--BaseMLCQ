public class Snippet__5506218 {

    private byte[] headerEncode() {
            this.makeCustomHeaderToNet();
            if (SerializeType.ROCKETMQ == serializeTypeCurrentRPC) {
                return RocketMQSerializable.rocketMQProtocolEncode(this);
            } else {
                return RemotingSerializable.encode(this);
            }
        }

}
