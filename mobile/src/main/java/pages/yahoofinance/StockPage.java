package pages.yahoofinance;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pages.BasePage;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/22/17
 * Time: 5:05 PM
 */
public class StockPage extends BasePage {

    private static final By yearRangeLocator = By.xpath("//li[span[contains(.,'52wk Range')]]/span[2]");
    private static final By currentQuoteLocator = By.xpath("//div[@id='hero']/section/p[1]/span[1]");
    private static final By keyStatisticsLinkLocator = By.xpath("//a[.='Key Statistics']");
    private static final By statisticsLabelLocator = By.xpath("//h2[.='Statistics']");
    private static final By dilutedEPSLocator = By.xpath("//li[span[contains(., 'Diluted EPS')]]/span[2]");

    public StockPage(AppiumDriver driver) {
        super(driver);
    }

    /**
     * Gets the 52 week highest value for the stock
     *
     * @return {@link Double} 52 week highest stock value
     */
    public double getYearHi() {
        waitForElementToBeVisible(yearRangeLocator);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        String text = getYearRanges()[1];
        try {
            return format.parse(text).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0d;
    }

    /**
     * Gets the 52 week lowest value for the stock
     *
     * @return {@link Double} 52 week lowest stock value
     */
    public double getYearLo() {
        waitForElementToBeVisible(yearRangeLocator);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        String text = getYearRanges()[0];
        try {
            return format.parse(text).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0d;
    }

    /**
     * Extracts the highest and lowest values for the stock.
     *
     * @return highest and lowest yearly values for the stock
     */
    private String[] getYearRanges() {
        return find(yearRangeLocator).getText().split(" - ");
    }

    /**
     * Gets the current value for the stock
     *
     * @return {@link Double} current stock value
     */
    public double getCurrentQuote() {
        waitForElementToBeVisible(currentQuoteLocator);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        String text = find(currentQuoteLocator).getText();
        try {
            return format.parse(text).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0d;
    }

    /**
     * Gets the earnings per share for the stock
     *
     * @return {@link Double} earnings per share for the stock
     */
    public double getEPS() {
        waitForElementToBeVisible(keyStatisticsLinkLocator);
        click(keyStatisticsLinkLocator);
        waitForElementToBeVisible(statisticsLabelLocator);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        String text = find(dilutedEPSLocator).getText();
        try {
            return format.parse(text).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0d;
    }
}
