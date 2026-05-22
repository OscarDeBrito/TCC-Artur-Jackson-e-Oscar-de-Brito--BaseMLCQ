public class Snippet__5818834 {

    @Override
        GraphSONMapper.Builder configureBuilder(final GraphSONMapper.Builder builder) {
            // already set to 1.0 in AbstractGraphSONMessageSerializerV1d0
            return builder.addCustomModule(new GremlinServerModule())
                    .typeInfo(TypeInfo.NO_TYPES);
        }

}
