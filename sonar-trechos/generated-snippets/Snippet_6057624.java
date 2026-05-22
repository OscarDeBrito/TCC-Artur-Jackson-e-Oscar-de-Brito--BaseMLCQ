public class Snippet__6057624 {

    @Override
            protected UnresolvedPlaceholderException doBuild(final DittoHeaders dittoHeaders,
                    @Nullable final String message,
                    @Nullable final String description,
                    @Nullable final Throwable cause,
                    @Nullable final URI href) {
                return new UnresolvedPlaceholderException(dittoHeaders, message, description, cause, href);
            }

}
