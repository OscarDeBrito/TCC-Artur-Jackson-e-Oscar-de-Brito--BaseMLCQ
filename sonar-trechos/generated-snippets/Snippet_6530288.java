public class Snippet__6530288 {

    public Snippet__6530288(LifxLightContext context, LifxLightCommunicationHandler communicationHandler) {
            this.logId = context.getLogId();
            this.product = context.getProduct();
            this.fadeTime = context.getConfiguration().getFadeTime();
            this.pendingLightState = context.getPendingLightState();
            this.scheduler = context.getScheduler();
            this.communicationHandler = communicationHandler;
        }

}
