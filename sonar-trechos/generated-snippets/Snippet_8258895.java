private static class ModelFactory {
		public static IGrammarAwareElementType createModelElementType() {
			return new IGrammarAwareElementType("Model_ELEMENT_TYPE", Bug299237TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelRule());
		}
		public static IGrammarAwareElementType createModel_GroupElementType() {
			return new IGrammarAwareElementType("Model_Group_ELEMENT_TYPE", Bug299237TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getGroup());
		}
		public static IGrammarAwareElementType createModel_ModelKeyword_0ElementType() {
			return new IGrammarAwareElementType("Model_ModelKeyword_0_ELEMENT_TYPE", Bug299237TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getModelKeyword_0());
		}
		public static IGrammarAwareElementType createModel_NameAssignment_1ElementType() {
			return new IGrammarAwareElementType("Model_NameAssignment_1_ELEMENT_TYPE", Bug299237TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getNameAssignment_1());
		}
		public static IGrammarAwareElementType createModel_NameIDTerminalRuleCall_1_0ElementType() {
			return new IGrammarAwareElementType("Model_NameIDTerminalRuleCall_1_0_ELEMENT_TYPE", Bug299237TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getNameIDTerminalRuleCall_1_0());
		}
		public static IGrammarAwareElementType createModel_SemicolonKeyword_2ElementType() {
			return new IGrammarAwareElementType("Model_SemicolonKeyword_2_ELEMENT_TYPE", Bug299237TestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getModelAccess().getSemicolonKeyword_2());
		}
	}
