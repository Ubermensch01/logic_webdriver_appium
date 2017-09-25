import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.yahoofinance.StockPage;
import utils.BaseTest;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/22/17
 * Time: 2:33 PM
 */
public class WebAppTest extends BaseTest {
    @BeforeClass
    public void setUp(ITestContext context) throws Exception {
        // This method allows running the test directly through the IDE.
        super.setUp(context.getCurrentXmlTest().getParameter("device") == null
                ? "ios"
                : context.getCurrentXmlTest().getParameter("device"));
    }

    /*
    Find a financial web application that supplies information on the prices of stocks.
        Perform the following actions (for each set of input data):

        • Navigate to the application.
        • Enter the stock code string for a company (A) exchanged on the NYSE.
        • Retrieve the current price of stock A.
        • Retrieve the data of the 52 week high and low for this stock over the year from the data provided
        on the page (may involve scrolling).
        • Output where the current stock price is relative to the high and low end of the range. For
        example, “Today’s price of <current> is 10% lower than the 52 week high and 6% higher than the
        52 week low.”
        • Enter the stock code for a second company (B) traded on the NYSE.
        • Compare the Earnings Per share (EPS) of both stocks and output which company has a higher EPS
     */
    @Test
    public void test() {
        String stockA = "AMZN";
        String stockB = "AAPL";

        StockPage page = getStockPage(stockA);

        double hi = page.getYearHi();
        double current = page.getCurrentQuote();
        double lo = page.getYearLo();
        double amazonEPS = page.getEPS();

        double higherThanLo = (current / lo - 1) * 100;
        double lowerThanHi = (hi / current - 1) * 100;
        System.out.println(String.format("Today’s price of %s is %.2f%% lower than the 52 week high and %.2f%% higher than the 52 week low.", stockA, higherThanLo, lowerThanHi));
        
        double appleEPS = getStockPage(stockB).getEPS();
        System.out.println(String.format("Apple EPS is %.2f and Amazon EPS is %.2f", appleEPS, amazonEPS));
        System.out.println(amazonEPS > appleEPS ? amazonEPS : appleEPS);
    }
}
