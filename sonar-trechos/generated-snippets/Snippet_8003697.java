public class Scenario5BodyElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.xtext.ui.tests.editor.contentassist.ParametersTestLanguage.Scenario5Body");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(0);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Keyword cIncludeKeyword_0_0 = (Keyword)cGroup_0.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Keyword cFragmentKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		
		//fragment Scenario5Body <Include> *:
		//	<Include> 'include'
		//	| <!Include> 'fragment'?;
		@Override public ParserRule getRule() { return rule; }
		
		//<Include> 'include' | <!Include> 'fragment'?
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//<Include> 'include'
		public Group getGroup_0() { return cGroup_0; }
		
		//'include'
		public Keyword getIncludeKeyword_0_0() { return cIncludeKeyword_0_0; }
		
		//<!Include> 'fragment'?
		public Group getGroup_1() { return cGroup_1; }
		
		//'fragment'?
		public Keyword getFragmentKeyword_1_0() { return cFragmentKeyword_1_0; }
	}
