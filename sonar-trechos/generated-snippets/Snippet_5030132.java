@RestController
public class AuditController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AuditLogger auditLogger;

    @ApiImplicitParams( {@ApiImplicitParam(dataType = "String", name = "Authorization", paramType = "header")})
    @ApiOperation(value = "Generate an Audit event", notes = "Causes an Audit event to be logged", tags = {"Audit"})
    @PostMapping(value = "/event/log", produces = Versions.V1_0_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void logEvent(@RequestBody AuditDto auditDto) {
        try {
            Map<String, String> requestContextMap = auditDto.getRequestContextMap();
            if (requestContextMap != null) {
                for (Map.Entry<String, String> entry : requestContextMap.entrySet()) {
                    ThreadContext.put(entry.getKey(), entry.getValue());
                }
            }
            auditLogger.logEvent(auditDto.getEventName(), auditDto.getCatalogId(), auditDto.getProperties());
        } finally {
            ThreadContext.clearMap();
        }
    }
}
