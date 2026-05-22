public class Snippet__7777986 {

    @ExtensionPoint
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = StringInputRowParser.class)
    @JsonSubTypes(value = {
        @JsonSubTypes.Type(name = "string", value = StringInputRowParser.class),
        @JsonSubTypes.Type(name = "map", value = MapInputRowParser.class),
        @JsonSubTypes.Type(name = "noop", value = NoopInputRowParser.class)
    })
    public interface InputRowParser<T>
    {
      /**
       * Parse an input into list of {@link InputRow}. List can contains null for rows that should be thrown away,
       * or throws {@code ParseException} if the input is unparseable. This method should never return null otherwise
       * lots of things will break.
       */
      @NotNull
      default List<InputRow> parseBatch(T input)
      {
        return Utils.nullableListOf(parse(input));
      }

      /**
       * Parse an input into an {@link InputRow}. Return null if this input should be thrown away, or throws
       * {@code ParseException} if the input is unparseable.
       */
      @Deprecated
      @Nullable
      default InputRow parse(T input)
      {
        return null;
      }

      ParseSpec getParseSpec();

      InputRowParser withParseSpec(ParseSpec parseSpec);
    }

}
