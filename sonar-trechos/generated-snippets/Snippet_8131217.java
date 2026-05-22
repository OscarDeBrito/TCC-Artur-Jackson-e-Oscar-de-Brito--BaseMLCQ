@SuppressWarnings("all")
public class TestLanguageIdeModule extends AbstractTestLanguageIdeModule {
  public Class<? extends ILanguageServerExtension> bindLanguageServerExtension() {
    return TestLangLSPExtension.class;
  }
  
  public Class<? extends ICodeLensResolver> bindICodeLensResolver() {
    return CodeLensService.class;
  }
  
  public Class<? extends ICodeLensService> bindICodeLensService() {
    return CodeLensService.class;
  }
  
  public Class<? extends ICodeActionService> bindICodeActionService() {
    return CodeActionService.class;
  }
  
  public Class<? extends IExecutableCommandService> bindIExecutableCommandService() {
    return TestLanguageExecutableCommandService.class;
  }
  
  public Class<? extends IdeContentProposalCreator> bindIdeContentProposalCreator() {
    return TestLanguageProposalCreator.class;
  }
  
  public Class<? extends IdeContentProposalProvider> bindIdeContentProposalProvider() {
    return TestLanguageIdeContentProposalProvider.class;
  }
}
