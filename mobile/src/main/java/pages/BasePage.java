package pages;

import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/22/17
 * Time: 5:05 PM
 */
public class BasePage {
    private static long POLLING_PERIOD = 200l;

    protected AppiumDriver driver;
    private Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * Finds an element identified by the locator.
     *
     * @param locator locator that identifies the {@link WebElement} to be found.
     * @return {@link WebElement}
     */
    public WebElement find(By locator) {
        logger.info(String.format("Locating element '%s'", locator.toString()));

        return new WebDriverWait(driver, POLLING_PERIOD).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Clicks and element identified by the locator.
     *
     * @param locator locator that identifies the {@link WebElement} to be clicked.
     */
    public void click(By locator) {
        logger.info(String.format("Clicking element '%s'", locator.toString()));
        find(locator).click();
    }

    /**
     * Types text into the element identified by the locator. This method appends the input to the existing text.
     *
     * @param text    text to type
     * @param locator locator that identifies the {@link WebElement} to be typed into
     */
    public void type(String text,
                     By locator) {
        type(text, true, locator);
    }

    /**
     * Types text into the element identified by the locator.
     *
     * @param text    text to type
     * @param append  whether to append the input or to clear existring text
     * @param locator locator that identifies the {@link WebElement} to be typed into
     */
    public void type(String text,
                     boolean append,
                     By locator) {
        WebElement element = find(locator);
        if (append) {
            element.clear();
        }

        logger.info(String.format("Typing '%s' in element '%s'", text, locator.toString()));
        for (char character : text.toCharArray()) {
            element.sendKeys(String.valueOf(character));
        }
    }

    /**
     * Method that waits until the {@link WebElement} is visible.
     *
     * @param locator locator identifying the element to wait for
     * @return instance of {@link WebElement}
     */
    protected WebElement waitForElementToBeVisible(final By locator) {
        FluentWait<AppiumDriver> wait = new FluentWait<>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS);

        return wait.until(ExpectedConditions.visibilityOf(find(locator)));
    }
}
