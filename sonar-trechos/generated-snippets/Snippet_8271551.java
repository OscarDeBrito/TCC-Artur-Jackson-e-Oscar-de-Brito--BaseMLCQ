private static class TerminalRuleCallFactory {
		public static IGrammarAwareElementType createTerminalRuleCallElementType() {
			return new IGrammarAwareElementType("TerminalRuleCall_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getTerminalRuleCallRule());
		}
		public static IGrammarAwareElementType createTerminalRuleCall_RuleAssignmentElementType() {
			return new IGrammarAwareElementType("TerminalRuleCall_RuleAssignment_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getTerminalRuleCallAccess().getRuleAssignment());
		}
		public static IGrammarAwareElementType createTerminalRuleCall_RuleAbstractRuleCrossReference_0ElementType() {
			return new IGrammarAwareElementType("TerminalRuleCall_RuleAbstractRuleCrossReference_0_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getTerminalRuleCallAccess().getRuleAbstractRuleCrossReference_0());
		}
		public static IGrammarAwareElementType createTerminalRuleCall_RuleAbstractRuleRuleIDParserRuleCall_0_1ElementType() {
			return new IGrammarAwareElementType("TerminalRuleCall_RuleAbstractRuleRuleIDParserRuleCall_0_1_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getTerminalRuleCallAccess().getRuleAbstractRuleRuleIDParserRuleCall_0_1());
		}
	}
