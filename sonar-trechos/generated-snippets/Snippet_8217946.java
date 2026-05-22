private static class AnnotationFactory {
		public static IGrammarAwareElementType createAnnotationElementType() {
			return new IGrammarAwareElementType("Annotation_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getAnnotationRule());
		}
		public static IGrammarAwareElementType createAnnotation_GroupElementType() {
			return new IGrammarAwareElementType("Annotation_Group_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getAnnotationAccess().getGroup());
		}
		public static IGrammarAwareElementType createAnnotation_CommercialAtKeyword_0ElementType() {
			return new IGrammarAwareElementType("Annotation_CommercialAtKeyword_0_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getAnnotationAccess().getCommercialAtKeyword_0());
		}
		public static IGrammarAwareElementType createAnnotation_NameAssignment_1ElementType() {
			return new IGrammarAwareElementType("Annotation_NameAssignment_1_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getAnnotationAccess().getNameAssignment_1());
		}
		public static IGrammarAwareElementType createAnnotation_NameIDTerminalRuleCall_1_0ElementType() {
			return new IGrammarAwareElementType("Annotation_NameIDTerminalRuleCall_1_0_ELEMENT_TYPE", XtextLanguage.INSTANCE, GRAMMAR_ACCESS.getAnnotationAccess().getNameIDTerminalRuleCall_1_0());
		}
	}
