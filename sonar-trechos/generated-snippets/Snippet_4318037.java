public class Snippet__4318037 {

    @Override
        CostEstimate getFinalCostEstimate()
    		throws StandardException
    	{
    		if (getCandidateFinalCostEstimate() != null)
            {
    			return getCandidateFinalCostEstimate();
            }

    		CostEstimate leftCE = leftResultSet.getFinalCostEstimate();
    		CostEstimate rightCE = rightResultSet.getFinalCostEstimate();

    		setCandidateFinalCostEstimate( getNewCostEstimate() );
    		getCandidateFinalCostEstimate().setCost(
    			leftCE.getEstimatedCost() + rightCE.getEstimatedCost(),
    			getRowCountEstimate(leftCE.rowCount(), rightCE.rowCount()),
    			getSingleScanRowCountEstimate(leftCE.singleScanRowCount(),
    				rightCE.singleScanRowCount()));

    		return getCandidateFinalCostEstimate();
    	}

}
