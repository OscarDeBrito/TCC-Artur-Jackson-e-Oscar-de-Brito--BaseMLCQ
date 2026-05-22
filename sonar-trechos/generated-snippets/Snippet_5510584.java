public class Snippet__5510584 {

    @Override
    	public List<RangerPolicy> getServicePolicies(Long serviceId, SearchFilter filter) throws Exception {
    		if(LOG.isDebugEnabled()) {
    			LOG.debug("==> ServiceDBStore.getServicePolicies(" + serviceId + ")");
    		}

    		XXService service = daoMgr.getXXService().getById(serviceId);

    		if (service == null) {
    			throw new Exception("service does not exist - id='" + serviceId);
    		}

    		List<RangerPolicy> ret = getServicePolicies(service, filter);

    		if(LOG.isDebugEnabled()) {
    			LOG.debug("<== ServiceDBStore.getServicePolicies(" + serviceId + ") : policy-count=" + (ret == null ? 0 : ret.size()));
    		}
    		return ret;

    	}

}
