@Singleton
public class TheiaDeleteFileDialog {
  private final SeleniumWebDriverHelper seleniumWebDriverHelper;

  @Inject
  private TheiaDeleteFileDialog(SeleniumWebDriverHelper seleniumWebDriverHelper) {
    this.seleniumWebDriverHelper = seleniumWebDriverHelper;
  }

  public interface Locators {
    String DIALOG_TITLE_XPATH = "//div[@class='dialogBlock']//div[text()='Delete File']";
    String OK_BUTTON_XPATH = "//div[@class='dialogBlock']//button[text()='OK']";
    String CANCEL_BUTTON_XPATH = "//div[@class='dialogBlock']//button[text()='Cancel']";
  }

  public void waitDialog() {
    seleniumWebDriverHelper.waitVisibility(By.xpath(DIALOG_TITLE_XPATH));
  }

  public void waitDialogDesapearance() {
    seleniumWebDriverHelper.waitInvisibility(By.xpath(DIALOG_TITLE_XPATH));
  }

  public void clickOkButton() {
    seleniumWebDriverHelper.waitAndClick(By.xpath(OK_BUTTON_XPATH));
  }

  public void clickCancelButton() {
    seleniumWebDriverHelper.waitAndClick(By.xpath(CANCEL_BUTTON_XPATH));
  }
}
