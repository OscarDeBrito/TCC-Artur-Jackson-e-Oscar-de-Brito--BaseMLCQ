public final class Utils {

  private static final String DELIMITER = "-";

  /**
   * Parse a string of multiple IDs.
   *
   * @param ids A string containing multiple IDs
   * @param factory An Identifier factory
   * @param <T> A type
   * @return A list of identifier
   */
  public static <T extends Identifier> List<T> parseList(final String ids, final IdentifierFactory factory) {
    final List<T> result = new ArrayList<>();
    for (final String token : ids.split(DELIMITER)) {
      result.add((T) factory.getNewInstance(token.trim()));
    }
    return result;
  }

  public static String listToString(final List<ComparableIdentifier> ids) {
    return StringUtils.join(ids, DELIMITER);
  }

  public static List<Integer> createUniformCounts(final int elemSize, final int childSize) {
    final int remainder = elemSize % childSize;
    final int quotient = elemSize / childSize;
    final ArrayList<Integer> result = new ArrayList<>(childSize);
    result.addAll(Collections.nCopies(remainder, quotient + 1));
    result.addAll(Collections.nCopies(childSize - remainder, quotient));
    return Collections.unmodifiableList(result);
  }

  private static class AddressComparator implements Comparator<Inet4Address> {
    @Override
    public int compare(final Inet4Address aa, final Inet4Address ba) {
      final byte[] a = aa.getAddress();
      final byte[] b = ba.getAddress();
      // local subnet comes after all else.
      if (a[0] == 127 && b[0] != 127) {
        return 1;
      }
      if (a[0] != 127 && b[0] == 127) {
        return -1;
      }
      for (int i = 0; i < 4; i++) {
        if (a[i] < b[i]) {
          return -1;
        }
        if (a[i] > b[i]) {
          return 1;
        }
      }
      return 0;
    }
  }

  public static ReefNetworkGroupCommProtos.GroupCommMessage bldGCM(
      final ReefNetworkGroupCommProtos.GroupCommMessage.Type msgType,
      final Identifier from, final Identifier to, final byte[]... elements) {

    final ReefNetworkGroupCommProtos.GroupCommMessage.Builder gcmBuilder =
        ReefNetworkGroupCommProtos.GroupCommMessage.newBuilder()
            .setType(msgType)
            .setSrcid(from.toString())
            .setDestid(to.toString());

    final ReefNetworkGroupCommProtos.GroupMessageBody.Builder bodyBuilder =
        ReefNetworkGroupCommProtos.GroupMessageBody.newBuilder();

    for (final byte[] element : elements) {
      bodyBuilder.setData(ByteString.copyFrom(element));
      gcmBuilder.addMsgs(bodyBuilder.build());
    }

    return gcmBuilder.build();
  }

  /**
   * Empty private constructor to prohibit instantiation of utility class.
   */
  private Utils() {
  }
}
