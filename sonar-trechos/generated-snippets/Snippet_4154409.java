public final class JUnitHelper {

    public static final String JUNIT_PARAMETERS = "org.apache.chemistry.opencmis.tck.junit.parameters";

    private JUnitHelper() {
    }

    public static void run(CmisTest test) throws Exception {
        run(new WrapperCmisTestGroup(test));
    }

    public static void run(CmisTestGroup group) throws Exception {
        JUnitRunner runner = new JUnitRunner();

        String parametersFile = System.getProperty(JUNIT_PARAMETERS);
        if (parametersFile == null) {
            runner.setParameters(null);
        } else {
            runner.loadParameters(new File(parametersFile));
        }

        runner.addGroup(group);
        runner.run(new JUnitProgressMonitor());

        CmisTestReport report = new TextReport();
        report.createReport(runner.getParameters(), runner.getGroups(), new PrintWriter(System.out));

        checkForFailures(runner);
    }

    private static void checkForFailures(JUnitRunner runner) {
        for (CmisTestGroup group : runner.getGroups()) {
            for (CmisTest test : group.getTests()) {
                for (CmisTestResult result : test.getResults()) {
                    if (result.getStatus().getLevel() >= CmisTestResultStatus.FAILURE.getLevel()) {
                        Assert.fail(result.getMessage());
                    }
                }
            }
        }
    }

    private static class JUnitRunner extends AbstractRunner {
    }

    private static class JUnitProgressMonitor implements CmisTestProgressMonitor {

        @Override
        @SuppressWarnings("PMD.SystemPrintln")
        public void startGroup(CmisTestGroup group) {
            System.out.println(group.getName() + " (" + group.getTests().size() + " tests)");
        }

        @Override
        public void endGroup(CmisTestGroup group) {
        }

        @Override
        @SuppressWarnings("PMD.SystemPrintln")
        public void startTest(CmisTest test) {
            System.out.println("  " + test.getName());
        }

        @Override
        public void endTest(CmisTest test) {
        }

        @Override
        @SuppressWarnings("PMD.SystemPrintln")
        public void message(String msg) {
            System.out.println(msg);
        }
    }
}
