private static class IdOrKeywordFactory {
		public static IGrammarAwareElementType createIdOrKeywordElementType() {
			return new IGrammarAwareElementType("IdOrKeyword_ELEMENT_TYPE", ParametersTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getIdOrKeywordRule());
		}
		public static IGrammarAwareElementType createIdOrKeyword_AlternativesElementType() {
			return new IGrammarAwareElementType("IdOrKeyword_Alternatives_ELEMENT_TYPE", ParametersTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getIdOrKeywordAccess().getAlternatives());
		}
		public static IGrammarAwareElementType createIdOrKeyword_Group_0ElementType() {
			return new IGrammarAwareElementType("IdOrKeyword_Group_0_ELEMENT_TYPE", ParametersTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getIdOrKeywordAccess().getGroup_0());
		}
		public static IGrammarAwareElementType createIdOrKeyword_KeywordKeyword_0_0ElementType() {
			return new IGrammarAwareElementType("IdOrKeyword_KeywordKeyword_0_0_ELEMENT_TYPE", ParametersTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getIdOrKeywordAccess().getKeywordKeyword_0_0());
		}
		public static IGrammarAwareElementType createIdOrKeyword_IDTerminalRuleCall_1ElementType() {
			return new IGrammarAwareElementType("IdOrKeyword_IDTerminalRuleCall_1_ELEMENT_TYPE", ParametersTestLanguageLanguage.INSTANCE, GRAMMAR_ACCESS.getIdOrKeywordAccess().getIDTerminalRuleCall_1());
		}
	}
