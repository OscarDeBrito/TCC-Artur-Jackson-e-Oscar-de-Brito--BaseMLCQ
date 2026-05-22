public class Snippet__7410586 {

    @SVMToHotSpot(OnFailure)
        @Override
        public void onFailure(CompilableTruffleAST compilable, String reason, boolean bailout, boolean permanentBailout) {
            JObject hsCompilable = ((HSCompilableTruffleAST) compilable).getHandle();
            JNIEnv env = HotSpotToSVMScope.env();
            JString hsReason = createHSString(env, reason);
            callOnFailure(env, getHandle(), hsCompilable, hsReason, bailout, permanentBailout);
        }

}
