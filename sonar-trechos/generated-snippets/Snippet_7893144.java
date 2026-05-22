public class Snippet__7893144 {

    public CxxToolFlags getNonIncludePathFlags(
          SourcePathResolver resolver, Optional<PrecompiledHeaderData> pch, Preprocessor preprocessor) {
        ExplicitCxxToolFlags.Builder builder = CxxToolFlags.explicitBuilder();
        ExplicitCxxToolFlags.addCxxToolFlags(builder, getOtherFlags());
        if (pch.isPresent()) {
          builder.addAllRuleFlags(
              StringArg.from(
                  preprocessor.prefixOrPCHArgs(
                      pch.get().isPrecompiled(), resolver.getAbsolutePath(pch.get().getHeader()))));
        }
        return builder.build();
      }

}
