public class PherfMainIT extends ResultBaseTestIT {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testPherfMain() {
        String[] args = { "-q",
                "--scenarioFile", ".*prod_test_unsalted_scenario.*",
                "-m", "--monitorFrequency", "10" };
        Pherf.main(args);
    }
}
