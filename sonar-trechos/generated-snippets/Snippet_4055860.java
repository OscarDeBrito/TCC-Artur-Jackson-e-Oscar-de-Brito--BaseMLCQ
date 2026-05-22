public static class OutgoingMessage implements Serializable {
    /** Underlying (encoded) element. */
    public final byte[] elementBytes;

    public final Map<String, String> attributes;

    /** Timestamp for element (ms since epoch). */
    public final long timestampMsSinceEpoch;

    /**
     * If using an id attribute, the record id to associate with this record's metadata so the
     * receiver can reject duplicates. Otherwise {@literal null}.
     */
    @Nullable public final String recordId;

    public OutgoingMessage(
        byte[] elementBytes,
        Map<String, String> attributes,
        long timestampMsSinceEpoch,
        @Nullable String recordId) {
      this.elementBytes = elementBytes;
      this.attributes = attributes;
      this.timestampMsSinceEpoch = timestampMsSinceEpoch;
      this.recordId = recordId;
    }

    @Override
    public String toString() {
      return String.format(
          "OutgoingMessage(%db, %dms)", elementBytes.length, timestampMsSinceEpoch);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      OutgoingMessage that = (OutgoingMessage) o;

      return timestampMsSinceEpoch == that.timestampMsSinceEpoch
          && Arrays.equals(elementBytes, that.elementBytes)
          && Objects.equal(attributes, that.attributes)
          && Objects.equal(recordId, that.recordId);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(
          Arrays.hashCode(elementBytes), attributes, timestampMsSinceEpoch, recordId);
    }
  }
