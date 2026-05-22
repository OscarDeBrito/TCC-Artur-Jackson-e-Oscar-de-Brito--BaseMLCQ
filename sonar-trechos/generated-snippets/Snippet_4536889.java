@Service
@CommandType(entity = "PROVISIONENTRIES", action = "CREATE")
public class CreateProvisioningEntriesRequestCommandHandler implements NewCommandSourceHandler {

    private final ProvisioningEntriesWritePlatformService provisioningEntriesWritePlatformService ;
    @Autowired
    public CreateProvisioningEntriesRequestCommandHandler(
            final ProvisioningEntriesWritePlatformService provisioningEntriesWritePlatformService) {
        this.provisioningEntriesWritePlatformService = provisioningEntriesWritePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(JsonCommand jsonCommand) {
        return this.provisioningEntriesWritePlatformService.createProvisioningEntries(jsonCommand) ;
    }

}
