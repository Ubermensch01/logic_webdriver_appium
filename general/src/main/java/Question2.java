import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/20/17
 * Time: 9:54 PM
 */
public class Question2 {

    /*
        Use your knowledge of WebDriver to fetch the following:
        a)	Display the third and fifth item from the above list. Your output should be:
        Applesauce, unsweetened
        Juice, Cranberry-apple drink
        b)	Fetch each food name and its servings and store them in a Map. Iterate through all the entries in the Map and print them.
     */

    private static WebDriver driver;
    private static List<WebElement> titles;
    private static List<WebElement> servings;

    public static void main(String[] args) {

        // one of the recent Gecko driver versions hangs on navigating to a *.html file
        setupChrome();
        driver = new ChromeDriver();

        // A valid markup and enclosing <div> tags would help.
        String url = String.valueOf(ClassLoader.getSystemResource("file.html"));
        driver.get(url);

        HashMap<String, String> foods = getFoods();

        System.out.println("3rd and 5th titles:");
        System.out.println(titles.get(2).getText());
        System.out.println(titles.get(4).getText());
        System.out.println();
        System.out.println("All title/serving pair printed out");
        for (Map.Entry food : foods.entrySet()) {
            System.out.println(String.format("%s - %s", food.getKey(), food.getValue()));
        }
        driver.quit();
    }

    /**
     * Automatically downloads the latest Chrome Driver binaries.
     */
    private static void setupChrome() {
        BrowserManager driverManager = ChromeDriverManager.getInstance();
        driverManager.setup();
        System.out.println(String.format("Chrome driver version : %s", driverManager.getDownloadedVersion()));
    }

    /**
     * Retrieves food key value pairs.
     *
     * @return {@link HashMap}
     */
    private static HashMap<String, String> getFoods() {
        titles = driver.findElements(By.cssSelector("div>span.title"));
        servings = driver.findElements(By.cssSelector("div>span.description"));

        // A verification to avoid a scenario where there are inconsistencies in the number of titles and servings.
        // Scenario with multiple inconsistencies might slip anyway though.
        if (titles.size() != servings.size())
            throw new RuntimeException("number of food title not equal to number of servings");
        HashMap<String, String> foods = new HashMap<>();
        for (int i = 0; i < titles.size(); i++) {
            foods.put(titles.get(i).getText(), servings.get(i).getText());
        }

        return foods;
    }
}
