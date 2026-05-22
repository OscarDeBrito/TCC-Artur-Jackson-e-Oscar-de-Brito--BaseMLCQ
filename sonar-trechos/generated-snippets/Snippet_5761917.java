public class Snippet__5761917 {

    @Override 
    	public boolean equals(Object that) {
    		if( !(that instanceof PlanningCoCodingGroup) )
    			return false;
		
    		PlanningCoCodingGroup thatgrp = (PlanningCoCodingGroup) that;
    		return Arrays.equals(_colIndexes, thatgrp._colIndexes);
    	}

}
