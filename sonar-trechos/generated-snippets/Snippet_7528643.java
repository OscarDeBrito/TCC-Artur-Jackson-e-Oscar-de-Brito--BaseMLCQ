public class Snippet__7528643 {

    public Snippet__7528643(AmazonSQSAsync amazonSqs, ResourceIdResolver resourceIdResolver) {
    		this(amazonSqs, new DynamicQueueUrlDestinationResolver(amazonSqs, resourceIdResolver));
    	}

}
