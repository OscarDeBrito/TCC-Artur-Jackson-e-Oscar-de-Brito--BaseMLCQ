class DFA61 extends DFA {

        public DFA61(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 61;
            this.eot = dfa_20;
            this.eof = dfa_40;
            this.min = dfa_41;
            this.max = dfa_42;
            this.accept = dfa_43;
            this.special = dfa_25;
            this.transition = dfa_44;
        }
        public String getDescription() {
            return "1852:7: ( (lv_type_6_0= ruleJvmTypeReference ) )?";
        }
    }
