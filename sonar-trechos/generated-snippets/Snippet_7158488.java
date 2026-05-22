public class Snippet__7158488 {

    final int readIntBigEndian() throws SQLServerException {
            if (payloadOffset + 4 <= currentPacket.payloadLength) {
                int value = Util.readIntBigEndian(currentPacket.payload, payloadOffset);
                payloadOffset += 4;
                return value;
            }

            return Util.readIntBigEndian(readWrappedBytes(4), 0);
        }

}
