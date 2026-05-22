public class Snippet__6630204 {

    private static boolean multiPointRelateEnvelope_(MultiPoint multipoint_a,
    			Envelope envelope_b, double tolerance, int relation,
    			ProgressTracker progress_tracker) {
    		switch (relation) {
    		case Relation.disjoint:
    			return multiPointDisjointEnvelope_(multipoint_a, envelope_b,
    					tolerance, progress_tracker);

    		case Relation.within:
    			return multiPointWithinEnvelope_(multipoint_a, envelope_b,
    					tolerance, progress_tracker);

    		case Relation.contains:
    			return multiPointContainsEnvelope_(multipoint_a, envelope_b,
    					tolerance, progress_tracker);

    		case Relation.equals:
    			return multiPointEqualsEnvelope_(multipoint_a, envelope_b,
    					tolerance, progress_tracker);

    		case Relation.touches:
    			return multiPointTouchesEnvelope_(multipoint_a, envelope_b,
    					tolerance, progress_tracker);

    		case Relation.crosses:
    			return multiPointCrossesEnvelope_(multipoint_a, envelope_b,
    					tolerance, progress_tracker);

    		default:
    			break; // warning fix
    		}

    		return false;
    	}

}
