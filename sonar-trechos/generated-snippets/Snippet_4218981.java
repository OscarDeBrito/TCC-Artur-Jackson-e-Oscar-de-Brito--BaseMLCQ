public static class PrepareMessageSerializer implements MessageSerializer<PrepareMessage>
    {
        public void serialize(PrepareMessage message, DataOutputPlus out, int version) throws IOException
        {
            out.writeInt(message.tableIds.size());
            for (TableId tableId : message.tableIds)
                tableId.serialize(out);
            UUIDSerializer.serializer.serialize(message.parentRepairSession, out, version);
            out.writeInt(message.ranges.size());
            for (Range<Token> r : message.ranges)
            {
                MessagingService.validatePartitioner(r);
                Range.tokenSerializer.serialize(r, out, version);
            }
            out.writeBoolean(message.isIncremental);
            out.writeLong(message.timestamp);
            out.writeBoolean(message.isGlobal);
            out.writeInt(message.previewKind.getSerializationVal());
        }

        public PrepareMessage deserialize(DataInputPlus in, int version) throws IOException
        {
            int tableIdCount = in.readInt();
            List<TableId> tableIds = new ArrayList<>(tableIdCount);
            for (int i = 0; i < tableIdCount; i++)
                tableIds.add(TableId.deserialize(in));
            UUID parentRepairSession = UUIDSerializer.serializer.deserialize(in, version);
            int rangeCount = in.readInt();
            List<Range<Token>> ranges = new ArrayList<>(rangeCount);
            for (int i = 0; i < rangeCount; i++)
                ranges.add((Range<Token>) Range.tokenSerializer.deserialize(in, MessagingService.globalPartitioner(), version));
            boolean isIncremental = in.readBoolean();
            long timestamp = in.readLong();
            boolean isGlobal = in.readBoolean();
            PreviewKind previewKind = PreviewKind.deserialize(in.readInt());
            return new PrepareMessage(parentRepairSession, tableIds, ranges, isIncremental, timestamp, isGlobal, previewKind);
        }

        public long serializedSize(PrepareMessage message, int version)
        {
            long size;
            size = TypeSizes.sizeof(message.tableIds.size());
            for (TableId tableId : message.tableIds)
                size += tableId.serializedSize();
            size += UUIDSerializer.serializer.serializedSize(message.parentRepairSession, version);
            size += TypeSizes.sizeof(message.ranges.size());
            for (Range<Token> r : message.ranges)
                size += Range.tokenSerializer.serializedSize(r, version);
            size += TypeSizes.sizeof(message.isIncremental);
            size += TypeSizes.sizeof(message.timestamp);
            size += TypeSizes.sizeof(message.isGlobal);
            size += TypeSizes.sizeof(message.previewKind.getSerializationVal());
            return size;
        }
    }
