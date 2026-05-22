public class Bug301935TestLanguageSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
    @NotNull
    protected SyntaxHighlighter createHighlighter() {
        return Bug301935TestLanguageLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
    }

}
