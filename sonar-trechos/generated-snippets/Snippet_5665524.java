public class Snippet__5665524 {

    @Override
        protected boolean containsAllNamedAxes(final String[] axisNames) {
            if (axisNames != null) {
    next:       for (final String name : axisNames) {
                    for (final CoordinateAxis axis : netcdfCS.getCoordinateAxes()) {
                        if (name.equalsIgnoreCase(axis.getShortName())) {
                            continue next;
                        }
                    }
                    return false;
                }
            }
            return true;
        }

}
