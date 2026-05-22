public static class Rule implements TestRule {
        private final Object test;

        public Rule(final Object test) {
            this.test = test;
        }

        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    start(test.getClass());
                    composerInject(test);
                    base.evaluate();
                }
            };
        }
    }
