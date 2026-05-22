public class RemoveCommand extends GfshCommand {
  public static final String REGION_NOT_FOUND = "Region <%s> not found in any of the members";

  @CliMetaData(relatedTopic = {CliStrings.TOPIC_GEODE_DATA, CliStrings.TOPIC_GEODE_REGION})
  @CliCommand(value = {CliStrings.REMOVE}, help = CliStrings.REMOVE__HELP)
  public ResultModel remove(
      @CliOption(key = {CliStrings.REMOVE__KEY}, help = CliStrings.REMOVE__KEY__HELP,
          specifiedDefaultValue = "") String key,
      @CliOption(key = {CliStrings.REMOVE__REGION}, mandatory = true,
          help = CliStrings.REMOVE__REGION__HELP,
          optionContext = ConverterHint.REGION_PATH) String regionPath,
      @CliOption(key = CliStrings.REMOVE__ALL, help = CliStrings.REMOVE__ALL__HELP,
          specifiedDefaultValue = "true", unspecifiedDefaultValue = "false") boolean removeAllKeys,
      @CliOption(key = {CliStrings.REMOVE__KEYCLASS},
          help = CliStrings.REMOVE__KEYCLASS__HELP) String keyClass) {
    Cache cache = getCache();

    if (!removeAllKeys && (key == null)) {
      return new ResultModel().createError(CliStrings.REMOVE__MSG__KEY_EMPTY);
    }

    if (removeAllKeys) {
      authorize(Resource.DATA, Operation.WRITE, regionPath);
    } else {
      authorize(Resource.DATA, Operation.WRITE, regionPath, key);
    }

    key = DataCommandsUtils.makeBrokenJsonCompliant(key);

    Region region = cache.getRegion(regionPath);
    DataCommandFunction removefn = new DataCommandFunction();
    DataCommandResult dataResult;
    if (region == null) {
      Set<DistributedMember> memberList = findAnyMembersForRegion(regionPath);

      if (CollectionUtils.isEmpty(memberList)) {
        return new ResultModel().createError(String.format(REGION_NOT_FOUND, regionPath));
      }

      DataCommandRequest request = new DataCommandRequest();
      request.setCommand(CliStrings.REMOVE);
      request.setKey(key);
      request.setKeyClass(keyClass);
      request.setRemoveAllKeys(removeAllKeys ? "ALL" : null);
      request.setRegionName(regionPath);
      dataResult = callFunctionForRegion(request, removefn, memberList);
    } else {
      dataResult = removefn.remove(key, keyClass, regionPath, removeAllKeys ? "ALL" : null,
          (InternalCache) cache);
    }

    dataResult.setKeyClass(keyClass);

    return dataResult.toResultModel();
  }
}
