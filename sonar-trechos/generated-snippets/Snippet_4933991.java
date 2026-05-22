public static class GleSYSBooleanAdapter extends TypeAdapter<GleSYSBoolean> {

      @Override
      public void write(JsonWriter writer, GleSYSBoolean value) throws IOException {
         writer.value(value.getValue() ? "yes" : "no");
      }

      @Override
      public GleSYSBoolean read(JsonReader in) throws IOException {
         if (in.peek() == JsonToken.BOOLEAN) {
            return new GleSYSBoolean(in.nextBoolean());
         } else if (in.peek() == JsonToken.NULL) {
            return GleSYSBoolean.FALSE;
         } else {
            return new GleSYSBoolean(Objects.equal(in.nextString(), "yes"));
         }
      }

   }
