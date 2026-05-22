public class Snippet__4434430 {

    private void goNextPage() {
            LOGGER.info("Navigating to next page...");
            WebElement nextButton = driver.findElement(By.xpath(ACTIVE_NXT_BTN));
            nextButton.click();
            waitForElement(expectedElement, DEFAULT_TIMEOUT, "Next page didn't load");
        }

}
