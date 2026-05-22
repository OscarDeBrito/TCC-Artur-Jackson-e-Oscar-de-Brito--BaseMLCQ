public class AbsPathChecker extends XPathVisitor
{
	private boolean m_isAbs = true;
	
	/**
	 * Process the LocPathIterator to see if it contains variables 
	 * or functions that may make it context dependent.
	 * @param path LocPathIterator that is assumed to be absolute, but needs checking.
	 * @return true if the path is confirmed to be absolute, false if it 
	 * may contain context dependencies.
	 */
	public boolean checkAbsolute(LocPathIterator path)
	{
		m_isAbs = true;
		path.callVisitors(null, this);
		return m_isAbs;
	}
	
	/**
	 * Visit a function.
	 * @param owner The owner of the expression, to which the expression can 
	 *              be reset if rewriting takes place.
	 * @param func The function reference object.
	 * @return true if the sub expressions should be traversed.
	 */
	public boolean visitFunction(ExpressionOwner owner, Function func)
	{
		if((func instanceof FuncCurrent) ||
		   (func instanceof FuncExtFunction))
			m_isAbs = false;
		return true;
	}
	
	/**
	 * Visit a variable reference.
	 * @param owner The owner of the expression, to which the expression can 
	 *              be reset if rewriting takes place.
	 * @param var The variable reference object.
	 * @return true if the sub expressions should be traversed.
	 */
	public boolean visitVariableRef(ExpressionOwner owner, Variable var)
	{
		m_isAbs = false;
		return true;
	}
}
