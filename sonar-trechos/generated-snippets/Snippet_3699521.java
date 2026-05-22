public class Snippet__3699521 {

    @java.lang.Override
          public com.alibaba.otter.canal.protocol.CanalPacket.Unsub buildPartial() {
            com.alibaba.otter.canal.protocol.CanalPacket.Unsub result = new com.alibaba.otter.canal.protocol.CanalPacket.Unsub(this);
            result.destination_ = destination_;
            result.clientId_ = clientId_;
            result.filter_ = filter_;
            onBuilt();
            return result;
          }

}
