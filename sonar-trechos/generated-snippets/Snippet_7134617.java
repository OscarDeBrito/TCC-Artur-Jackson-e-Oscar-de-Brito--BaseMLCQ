public class ActivityFeedRequest extends FeedUserRequest {

    @Override
    public ActivityFeedResponse send() throws NetworkRequestException {
        ServiceResponse<FeedResponseActivityView> serviceResponse;
        try {
            serviceResponse =
                    MY_FOLLOWING.getActivities(authorization, getCursor(), getBatchSize());
        } catch (ServiceException|IOException e) {
            throw new NetworkRequestException(e.getMessage());
        }
        checkResponseCode(serviceResponse);

        return new ActivityFeedResponse(serviceResponse.getBody());
    }
}
