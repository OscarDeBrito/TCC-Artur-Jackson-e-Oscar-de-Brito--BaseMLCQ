public class NotExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.generator.parser.antlr.debug.SimpleAntlr.NotExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cPrimaryExpressionParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Action cNotExpressionAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cExclamationMarkKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cValueAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cValueNotExpressionParserRuleCall_1_2_0 = (RuleCall)cValueAssignment_1_2.eContents().get(0);
		
		//NotExpression Expression:
		//	PrimaryExpression | {NotExpression} '!' value=NotExpression
		@Override public ParserRule getRule() { return rule; }

		//PrimaryExpression | {NotExpression} '!' value=NotExpression
		public Alternatives getAlternatives() { return cAlternatives; }

		//PrimaryExpression
		public RuleCall getPrimaryExpressionParserRuleCall_0() { return cPrimaryExpressionParserRuleCall_0; }

		//{NotExpression} '!' value=NotExpression
		public Group getGroup_1() { return cGroup_1; }

		//{NotExpression}
		public Action getNotExpressionAction_1_0() { return cNotExpressionAction_1_0; }

		//'!'
		public Keyword getExclamationMarkKeyword_1_1() { return cExclamationMarkKeyword_1_1; }

		//value=NotExpression
		public Assignment getValueAssignment_1_2() { return cValueAssignment_1_2; }

		//NotExpression
		public RuleCall getValueNotExpressionParserRuleCall_1_2_0() { return cValueNotExpressionParserRuleCall_1_2_0; }
	}
