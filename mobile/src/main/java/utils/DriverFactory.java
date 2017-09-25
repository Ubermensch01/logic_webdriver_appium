package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/21/17
 * Time: 8:34 PM
 */
public class DriverFactory {
    public static DesiredCapabilities ANDROID;
    public static DesiredCapabilities ANDROID_MAPS;
    public static DesiredCapabilities IOS;
    public static DesiredCapabilities IOS_MAPS;

    static {
        ANDROID = DesiredCapabilities.android();
        ANDROID.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        ANDROID.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        ANDROID_MAPS = DesiredCapabilities.android();
        ANDROID_MAPS.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        ANDROID_MAPS.setCapability(MobileCapabilityType.APP_PACKAGE, "com.google.android.apps.maps");

        // This is what prevented me from completing an assignment with the maps application,
        // I could not find a correct Activity to start the Google Maps application on Android.
        // At this point, replicating this test on IOS is impossible due to the fact that GPS settings are not yet
        // exposed in Apple devices APIs.
        ANDROID_MAPS.setCapability(MobileCapabilityType.APP_ACTIVITY, ".Maps");

        IOS = DesiredCapabilities.iphone();
        IOS.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
        IOS.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        IOS.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");

        IOS_MAPS = DesiredCapabilities.iphone();
        IOS_MAPS.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
        IOS_MAPS.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        IOS_MAPS.setCapability("bundleId", "com.apple.Maps");
        IOS_MAPS.setCapability("autoAcceptAlerts", true);
        IOS_MAPS.setCapability("locationServicesAuthorized", true);
        IOS_MAPS.setCapability("locationServicesEnabled", true);
    }

    /**
     * Instantiates an {@link AppiumDriver} with specified {@link DesiredCapabilities}
     *
     * @param capabilities configuration of {@link AppiumDriver}
     * @return {@link AppiumDriver}
     */
    public static AppiumDriver getDriver(DesiredCapabilities capabilities) {
        String hubUrl = "http://127.0.0.1:4723/wd/hub";
        URL url = null;
        try {
            url = new URL(hubUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        AppiumDriver driver = null;
        switch (capabilities.getPlatform()) {
            case ANDROID:
                driver = new AndroidDriver(url, capabilities);
                break;
            case MAC:
                driver = new IOSDriver(url, capabilities);
                break;
            default:
                break;
        }
        return driver;
    }
}
