public class InstantTypeAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant>
{
    public static final InstantTypeAdapter DEFAULT_INSTANCE = new InstantTypeAdapter ();

    private final DateTimeFormatter formatter;

    public InstantTypeAdapter ( final DateTimeFormatter formatter )
    {
        this.formatter = formatter.withLocale ( Locale.US );
    }

    public InstantTypeAdapter ()
    {
        this ( DateTimeFormatter.ISO_INSTANT.withLocale ( Locale.US ) );
    }

    @Override
    public Instant deserialize ( final JsonElement json, final Type typeOfT, final JsonDeserializationContext context ) throws JsonParseException
    {
        if ( ! ( json instanceof JsonPrimitive ) )
        {
            throw new JsonParseException ( "Timestamps should be encoded as JSON strings" );
        }

        return Instant.from ( this.formatter.parse ( json.getAsString () ) );
    }

    @Override
    public JsonElement serialize ( final Instant src, final Type typeOfSrc, final JsonSerializationContext context )
    {
        return new JsonPrimitive ( this.formatter.format ( src ) );
    }

}
