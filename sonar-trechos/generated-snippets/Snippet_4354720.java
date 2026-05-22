public class Snippet__4354720 {

    private void bindSQRTABS()
    			throws StandardException
    	{
    		TypeId	operandType;
    		int 	jdbcType;

    		/*
    		** Check the type of the operand 
    		*/
    		operandType = operand.getTypeId();

    		/*
    	 	 * If the operand is not a build-in type, generate a bound conversion
    		 * tree to build-in types.
    		 */
    		if (operandType.userType() )
    		{
    			operand = operand.genSQLJavaSQLTree();
    		}
    		/* DB2 doesn't cast string types to numeric types for numeric functions  */

    		jdbcType = operandType.getJDBCTypeId();

    		/* Both SQRT and ABS are only allowed on numeric types */
    		if (!operandType.isNumericTypeId())
    			throw StandardException.newException(
    						SQLState.LANG_UNARY_FUNCTION_BAD_TYPE, 
    						getOperatorString(), operandType.getSQLTypeName());

    		/* For SQRT, if operand is not a DOUBLE, convert it to DOUBLE */
           if (kind == K_SQRT &&
                jdbcType != Types.DOUBLE)
    		{
                operand = new CastNode(
    					operand,
    					new DataTypeDescriptor(TypeId.getBuiltInTypeId(Types.DOUBLE), true),
    					getContextManager());
    			((CastNode) operand).bindCastNodeOnly();
    		}
    	}

}
