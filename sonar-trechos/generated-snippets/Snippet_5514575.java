@JsonAutoDetect(getterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL )
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VXMetricPolicyCount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Map<String,VXMetricServiceCount> policyCountList = new HashMap<String,VXMetricServiceCount>();
	protected long totalCount;
	
	/**
	 * Default constructor. This will set all the attributes to default value.
	 */
	public VXMetricPolicyCount() {	
	}	

	/**
	 * @return the policyCountList
	 */
	public Map<String, VXMetricServiceCount> getPolicyCountList() {
		return policyCountList;
	}

	/**
	 * @param policyCountList the policyCountList to set
	 */
	public void setPolicyCountList(Map<String, VXMetricServiceCount> policyCountList) {
		this.policyCountList = policyCountList;
	}

	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "VXMetricPolicyCount={totalCount="
				+ totalCount +", vXMetricServiceCount=["
				+ policyCountList.toString()
				 + "]}";
	}
}
