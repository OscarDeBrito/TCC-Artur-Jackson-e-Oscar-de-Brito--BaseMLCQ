@Beta
@Deprecated
public class ToStringHelper {

	/**
	 * @deprecated use {@link ToStringBuilder}
	 */
	@Deprecated
	public ToStringHelper() {
	}

	/**
	 * Creates a string representation of the given object by listing the internal state of all fields.
	 * 
	 * @param obj
	 *            the object that should be printed.
	 * @return the string representation. Never <code>null</code>.
	 * @deprecated use <code>new ToStringBuilder().addAllFields().toString()</code>
	 */
	@Deprecated
	public String toString(Object obj) {
		return new ToStringBuilder(obj).addAllFields().toString();
	}

}
