@Service
@CommandType(entity = "USER", action = "DELETE")
public class DeleteUserCommandHandler implements NewCommandSourceHandler {

    private final AppUserWritePlatformService writePlatformService;

    @Autowired
    public DeleteUserCommandHandler(final AppUserWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.writePlatformService.deleteUser(command.entityId());
    }
}
