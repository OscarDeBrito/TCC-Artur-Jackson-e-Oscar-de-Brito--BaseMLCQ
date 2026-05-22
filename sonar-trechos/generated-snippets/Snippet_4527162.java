public class Snippet__4527162 {

    @Transactional
        @Override
        public CommandProcessingResult processCommand(final JsonCommand command) {
            return this.depositAccountWritePlatformService.depositToRDAccount(command.entityId(), command);
        }

}
