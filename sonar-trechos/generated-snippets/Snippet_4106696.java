public class Snippet__4106696 {

    public Snippet__4106696(double dailyUsageRate, double amountUsedAverage,
    			double amountUsedVariance, SeedFactory seedFactory)
    	{
    		this.amountUsedAverage = amountUsedAverage;
    		this.amountUsedVariance = amountUsedVariance;

    		timestepSampler = new ExponentialSampler(dailyUsageRate, seedFactory);
    		R = new GaussianSampler(0.0, 1.0, seedFactory);
    	}

}
