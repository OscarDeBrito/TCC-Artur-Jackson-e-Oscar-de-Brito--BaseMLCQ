public interface HaveSpaceContract extends HostSystemProvider {
    String USER = "user";
    String PASSWORD = "password";

    default SimpleScriptedTestProtocol haveSpaceContractProtocol() throws Exception {
        return new SimpleScriptedTestProtocol("/org/apache/james/managesieve/scripts/", hostSystem())
                .withUser(USER, PASSWORD)
                .withLocale(Locale.US)
                .withPreparedCommand(system ->
                    ((ManageSieveHostSystem) system).setMaxQuota(USER, 50));
    }

    @Test
    default void haveSpaceShouldWork() throws Exception {
        haveSpaceContractProtocol()
            .withLocale(Locale.US)
            .run("havespace");
    }

}
