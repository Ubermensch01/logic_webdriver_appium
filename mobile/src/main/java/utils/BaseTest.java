package utils;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import pages.yahoofinance.StockPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is the basis for all tests, it contains crucial methods required for a test:
 * - setup methods that configure test environment
 * - teardown methods that clean up after test execution (e.g. properly disposing of the AppiumDriver instance, etc.)
 * - methods frequently used in tests
 * <p>
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/22/17
 * Time: 3:57 PM
 */
public class BaseTest {

    protected AppiumDriver driver;

    /**
     * This method configures corresponding capabilites to run the test against.
     *
     * @param device device code to load a corresponding set of capabilities
     */
    @Parameters({"browser"})
    protected void setUp(@org.testng.annotations.Optional("ios") String device) {
        switch (device) {
            default:
            case "ios":
                driver = DriverFactory.getDriver(DriverFactory.IOS);
                break;
            case "android":
                driver = DriverFactory.getDriver(DriverFactory.ANDROID);
                break;
            case "ios-maps":
                driver = DriverFactory.getDriver(DriverFactory.IOS_MAPS);
                break;
            case "android-maps":
                driver = DriverFactory.getDriver(DriverFactory.ANDROID_MAPS);
                break;
        }
    }

    /**
     * This method utilizes the url of finance.yahoo.com to navigate directly to the quote page.
     *
     * @param stockCode codename of a stock to navigate to.
     * @return instance of the {@link StockPage}
     */
    public StockPage getStockPage(String stockCode) {
        driver.get(String.format("https://finance.yahoo.com/quote/%s", stockCode));
        return new StockPage(driver);
    }

    /**
     * This method properly shuts down the AppiumDriver instance.
     */
    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    /**
     * This method would be useful for tests that require preparation through command line (e.g. installing apps
     * or looking up specific device emulators. This method should probably be extracted into a separate class to be
     * shared with the DriverFactory class.
     *
     * @param commandAndParameters command and parameters to execute
     * @return List of output strings.
     */
    protected List<String> executeCL(String... commandAndParameters) {
        ProcessBuilder builder = new ProcessBuilder(commandAndParameters);
        builder.redirectErrorStream(true);
        List<String> output = new LinkedList<>();
        try {
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                output.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
