private static class ToPagedIterable extends BaseToPagedIterable<Project, ListOptions> {
            @Inject ToPagedIterable(PacketApi api, Function<Href, ListOptions> hrefToOptions) {
                super(api, hrefToOptions);
            }

            @Override
            protected IterableWithMarker<Project> fetchPageUsingOptions(ListOptions options, Optional<Object> arg0) {
                return api.projectApi().list(options);
            }
        }
