public class Snippet__3738168 {

    public Builder clear() {
            super.clear();
            tableId_ = 0L;
            bitField0_ = (bitField0_ & ~0x00000001);
            schemaName_ = "";
            bitField0_ = (bitField0_ & ~0x00000002);
            tableName_ = "";
            bitField0_ = (bitField0_ & ~0x00000004);
            eventType_ = "";
            bitField0_ = (bitField0_ & ~0x00000008);
            if (oldKeysBuilder_ == null) {
              oldKeys_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000010);
            } else {
              oldKeysBuilder_.clear();
            }
            if (keysBuilder_ == null) {
              keys_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000020);
            } else {
              keysBuilder_.clear();
            }
            if (columnsBuilder_ == null) {
              columns_ = java.util.Collections.emptyList();
              bitField0_ = (bitField0_ & ~0x00000040);
            } else {
              columnsBuilder_.clear();
            }
            executeTime_ = 0L;
            bitField0_ = (bitField0_ & ~0x00000080);
            pairId_ = 0L;
            bitField0_ = (bitField0_ & ~0x00000100);
            syncMode_ = "";
            bitField0_ = (bitField0_ & ~0x00000200);
            syncConsistency_ = "";
            bitField0_ = (bitField0_ & ~0x00000400);
            size_ = 0L;
            bitField0_ = (bitField0_ & ~0x00000800);
            remedy_ = false;
            bitField0_ = (bitField0_ & ~0x00001000);
            sql_ = "";
            bitField0_ = (bitField0_ & ~0x00002000);
            ddlSchemaName_ = "";
            bitField0_ = (bitField0_ & ~0x00004000);
            hint_ = "";
            bitField0_ = (bitField0_ & ~0x00008000);
            withoutSchema_ = false;
            bitField0_ = (bitField0_ & ~0x00010000);
            return this;
          }

}
