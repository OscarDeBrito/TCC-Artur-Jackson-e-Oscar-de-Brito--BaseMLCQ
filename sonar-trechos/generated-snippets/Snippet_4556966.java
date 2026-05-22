public interface CodeValueReadPlatformService {

    Collection<CodeValueData> retrieveCodeValuesByCode(final String code);

    Collection<CodeValueData> retrieveAllCodeValues(final Long codeId);

    CodeValueData retrieveCodeValue(final Long codeValueId);
}
