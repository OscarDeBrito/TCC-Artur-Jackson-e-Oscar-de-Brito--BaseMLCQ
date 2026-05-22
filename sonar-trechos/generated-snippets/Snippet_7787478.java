public class AddDelta extends Delta
{
	/**
	 * Construct.
	 */
	AddDelta()
	{
	}

	/**
	 * Construct.
	 * 
	 * @param origpos
	 * @param rev
	 */
	public AddDelta(final int origpos, final Chunk rev)
	{
		init(new Chunk(origpos, 0), rev);
	}

	/**
	 * @see org.apache.wicket.util.diff.Delta#verify(java.util.List)
	 */
	@Override
	public void verify(final List<Object> target) throws PatchFailedException
	{
		if (original.first() > target.size())
		{
			throw new PatchFailedException("original.first() > target.size()");
		}
	}

	/**
	 * @see org.apache.wicket.util.diff.Delta#applyTo(java.util.List)
	 */
	@Override
	public void applyTo(final List<Object> target)
	{
		revised.applyAdd(original.first(), target);
	}

	/**
	 * @see org.apache.wicket.util.diff.Delta#toString(StringBuilder)
	 */
	@Override
	public void toString(final StringBuilder s)
	{
		s.append(original.anchor());
		s.append("a");
		s.append(revised.rangeString());
		s.append(Diff.NL);
		revised.toString(s, "> ", Diff.NL);
	}

	/**
	 * @see org.apache.wicket.util.diff.Delta#toRCSString(StringBuilder, String)
	 */
	@Override
	public void toRCSString(final StringBuilder s, final String EOL)
	{
		s.append("a");
		s.append(original.anchor());
		s.append(" ");
		s.append(revised.size());
		s.append(EOL);
		revised.toString(s, "", EOL);
	}

	/**
	 * @see org.apache.wicket.util.diff.Delta#accept(org.apache.wicket.util.diff.RevisionVisitor)
	 */
	@Override
	public void accept(final RevisionVisitor visitor)
	{
		visitor.visit(this);
	}
}
