public class Snippet__5414004 {

    private PDatum getParam(int index) throws SQLException {
            if (index <= 0 || index > params.length) {
                throw new SQLExceptionInfo.Builder(SQLExceptionCode.PARAM_INDEX_OUT_OF_BOUND)
                    .setMessage("The index is " + index + ". Must be between 1 and " + params.length)
                    .build().buildException();
            }
            PDatum param = params[index-1];
        
            if (param == EMPTY_DATUM) {
                //value at params[index-1] was never set.
                throw new SQLExceptionInfo.Builder(SQLExceptionCode.PARAM_VALUE_UNBOUND)
                    .setMessage("Parameter at index " + index + " is unbound").build().buildException();
            }
            return param;
        }

}
