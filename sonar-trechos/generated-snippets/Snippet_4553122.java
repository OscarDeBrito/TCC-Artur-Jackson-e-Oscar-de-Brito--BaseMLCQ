public class Snippet__4553122 {

    @Transactional
        @Override
        public CommandProcessingResult processCommand(JsonCommand jsonCommand) {
            return this.shareProductWritePlatformService.deleteShareProductDividend(jsonCommand.entityId());
        }

}
