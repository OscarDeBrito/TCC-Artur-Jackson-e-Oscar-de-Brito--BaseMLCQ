public class Snippet__4218038 {

    @Override
            public Terminal bind(QueryOptions options) throws InvalidRequestException
            {
                Term term = options.getJsonColumnValue(marker.bindIndex, column.name, marker.columns);
                return term == null
                     ? (defaultUnset ? Constants.UNSET_VALUE : null)
                     : term.bind(options);
            }

}
