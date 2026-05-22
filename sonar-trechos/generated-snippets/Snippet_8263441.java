private static class ExpressionFactory {
		public static IGrammarAwareElementType createExpressionElementType() {
			return new IGrammarAwareElementType("Expression_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionRule());
		}
		public static IGrammarAwareElementType createExpression_GroupElementType() {
			return new IGrammarAwareElementType("Expression_Group_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getGroup());
		}
		public static IGrammarAwareElementType createExpression_ExpressionAction_0ElementType() {
			return new IGrammarAwareElementType("Expression_ExpressionAction_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getExpressionAction_0());
		}
		public static IGrammarAwareElementType createExpression_UnorderedGroup_1ElementType() {
			return new IGrammarAwareElementType("Expression_UnorderedGroup_1_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getUnorderedGroup_1());
		}
		public static IGrammarAwareElementType createExpression_LeftSquareBracketKeyword_1_0ElementType() {
			return new IGrammarAwareElementType("Expression_LeftSquareBracketKeyword_1_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getLeftSquareBracketKeyword_1_0());
		}
		public static IGrammarAwareElementType createExpression_PrefixAssignment_1_1ElementType() {
			return new IGrammarAwareElementType("Expression_PrefixAssignment_1_1_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getPrefixAssignment_1_1());
		}
		public static IGrammarAwareElementType createExpression_PrefixSTRINGTerminalRuleCall_1_1_0ElementType() {
			return new IGrammarAwareElementType("Expression_PrefixSTRINGTerminalRuleCall_1_1_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getPrefixSTRINGTerminalRuleCall_1_1_0());
		}
		public static IGrammarAwareElementType createExpression_Group_2ElementType() {
			return new IGrammarAwareElementType("Expression_Group_2_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getGroup_2());
		}
		public static IGrammarAwareElementType createExpression_LeftSquareBracketKeyword_2_0ElementType() {
			return new IGrammarAwareElementType("Expression_LeftSquareBracketKeyword_2_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getLeftSquareBracketKeyword_2_0());
		}
		public static IGrammarAwareElementType createExpression_TermsAssignment_2_1ElementType() {
			return new IGrammarAwareElementType("Expression_TermsAssignment_2_1_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getTermsAssignment_2_1());
		}
		public static IGrammarAwareElementType createExpression_TermsSimpleTermParserRuleCall_2_1_0ElementType() {
			return new IGrammarAwareElementType("Expression_TermsSimpleTermParserRuleCall_2_1_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getTermsSimpleTermParserRuleCall_2_1_0());
		}
		public static IGrammarAwareElementType createExpression_RightSquareBracketKeyword_2_2ElementType() {
			return new IGrammarAwareElementType("Expression_RightSquareBracketKeyword_2_2_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getRightSquareBracketKeyword_2_2());
		}
		public static IGrammarAwareElementType createExpression_UnorderedGroup_3ElementType() {
			return new IGrammarAwareElementType("Expression_UnorderedGroup_3_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getUnorderedGroup_3());
		}
		public static IGrammarAwareElementType createExpression_RightSquareBracketKeyword_3_0ElementType() {
			return new IGrammarAwareElementType("Expression_RightSquareBracketKeyword_3_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getRightSquareBracketKeyword_3_0());
		}
		public static IGrammarAwareElementType createExpression_PostfixAssignment_3_1ElementType() {
			return new IGrammarAwareElementType("Expression_PostfixAssignment_3_1_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getPostfixAssignment_3_1());
		}
		public static IGrammarAwareElementType createExpression_PostfixSTRINGTerminalRuleCall_3_1_0ElementType() {
			return new IGrammarAwareElementType("Expression_PostfixSTRINGTerminalRuleCall_3_1_0_ELEMENT_TYPE", SimpleBacktrackingBug325745TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getExpressionAccess().getPostfixSTRINGTerminalRuleCall_3_1_0());
		}
	}
