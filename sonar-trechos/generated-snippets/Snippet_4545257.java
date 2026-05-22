@Service
@CommandType(entity = "TAXGROUP", action = "UPDATE")
public class UpdateTaxGroupCommandHandler implements NewCommandSourceHandler {

    private final TaxWritePlatformService taxWritePlatformService;

    @Autowired
    public UpdateTaxGroupCommandHandler(final TaxWritePlatformService taxWritePlatformService) {
        this.taxWritePlatformService = taxWritePlatformService;
    }

    @Override
    public CommandProcessingResult processCommand(JsonCommand jsonCommand) {
        return this.taxWritePlatformService.updateTaxGroup(jsonCommand.entityId(), jsonCommand);
    }

}
