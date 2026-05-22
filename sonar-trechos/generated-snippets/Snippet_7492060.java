public class Snippet__7492060 {

    @Override
    	public LinkRelation getItemResourceRel() {

    		LinkRelation fallback = domainTypeMapping.getItemResourceRel();

    		if (repositoryAnnotation != null) {
    			String rel = repositoryAnnotation.itemResourceRel();
    			return StringUtils.hasText(rel) ? LinkRelation.of(rel) : fallback;
    		}

    		return fallback;
    	}

}
