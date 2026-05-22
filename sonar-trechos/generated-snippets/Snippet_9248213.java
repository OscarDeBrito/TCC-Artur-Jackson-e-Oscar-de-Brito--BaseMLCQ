public static interface Completer {

        /** Dummy completer to be used when the symbol has been completed or
         * does not need completion.
         */
        public final static Completer NULL_COMPLETER = new Completer() {
            public void complete(Symbol sym) { }
            public boolean isTerminal() { return true; }
        };

        void complete(Symbol sym) throws CompletionFailure;

        /** Returns true if this completer is <em>terminal</em>. A terminal
         * completer is used as a place holder when the symbol is completed.
         * Calling complete on a terminal completer will not affect the symbol.
         *
         * The dummy NULL_COMPLETER and the GraphDependencies completer are
         * examples of terminal completers.
         *
         * @return true iff this completer is terminal
         */
        default boolean isTerminal() {
            return false;
        }
    }
