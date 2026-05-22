public class Snippet__9159804 {

    private void retrySetNullParamValue( ParameterName paramName, int paramIndex, 
                                             int parameterType,
                                             Exception lastException ) 
            throws DataException
        {
            switch( parameterType )
            {            
                case Types.CHAR:
                {
                    setString( paramName, paramIndex, null );
                    return;
                }
            
                case Types.DECIMAL:
                {
                    setBigDecimal( paramName, paramIndex, null );
                    return;
                }
                case Types.DATE:
                {
                    setDate( paramName, paramIndex, null );
                    return;
                }
            
                case Types.TIME:
                {
                    setTime( paramName, paramIndex, null );
                    return;
                }

                case Types.TIMESTAMP:
                {
                    setTimestamp( paramName, paramIndex, null );
                    return;
                }

                case Types.JAVA_OBJECT:
                {
                    setObject( paramName, paramIndex, null );
                    return;
                }
            
                default:
                    // metadata indicates primitive data types or types not supported for input parameter, 
                    // cannot retry with a different ODA API setter to assign 
                    // a null input parameter value
                
                    getLogger().logp( Level.SEVERE, sm_className, "retrySetNullParamValue",  //$NON-NLS-1$
                                    "Input parameter value is null; not able to retry, throws exception from underlying ODA driver." ); //$NON-NLS-1$
                
                    // not able to retry, throw last exception thrown by 
                    // the underlying ODA driver
                    throwSetParamValueLastException( lastException, "retrySetNullParamValue" ); //$NON-NLS-1$
            }
        }

}
