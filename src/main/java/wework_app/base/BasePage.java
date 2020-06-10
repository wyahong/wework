package wework_app.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    public BasePage() {
        startApp();
    }
    public void startApp(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.tencent.wework");
        desiredCapabilities.setCapability("appActivity", ".launch.LaunchSplashActivity");
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", "true");
        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        desiredCapabilities.setCapability("recreateChromeDriverSessions", true);

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AppiumDriver<>(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(this.driver, 10);
    }

    public By byText(String text){
        return By.xpath("//*[@text='" + text + "']");
    }

    public void click(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public void click(String text){
        findElement(text).click();
    }

    public void click(MobileElement element){
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public void click(By by, int index){
        driver.findElements(by).get(index).click();
    }

    public void sendKeys(By by, String keyword){
        driver.findElement(by).sendKeys(keyword);
    }

    public MobileElement findElement(By by){
        return driver.findElement(by);
    }

    public MobileElement findElement(String text){
        return driver.findElement(byText(text));
    }

    public List<MobileElement> getElements(By by){
        List<MobileElement> elements = new ArrayList<>();
        for(MobileElement e: driver.findElements(by)){
            elements.add(e);
        }
        return elements;
    }

    public boolean isExist(By by){
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getText(By by){
        return driver.findElement(by).getText();
    }

    //滑动
    public void swipe(double x1, double x2, double y1, double y2){
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point((int)(width*x1),(int)(height*y1))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(
                PointOption.point((int)(width*x2),(int)(height*y2))).release().perform();
    }

    /**
     * 滑动
     * @param mode  1-上下滑动   2-左右滑动
     * @param d1    滑动百分比
     * @param d2    滑动百分比
     */
    public void swipe(int mode, double d1, double d2){
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        TouchAction action = new TouchAction(driver);
        if(mode == 1){
            action.press(PointOption.point((int)(width*0.5),(int)(height*d1))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(
                    PointOption.point((int)(width*0.5),(int)(height*d2))).release().perform();
        }else if(mode == 2){
            action.press(PointOption.point((int)(width*d1), height)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(
                    PointOption.point((int)(width*d2), height)).release().perform();
        }else{
            System.out.println("模式不对：1(上下滑动） 或 2（左右滑动）");
        }

    }

    //按压元素滑动
    public void swipe(By by, double x, double y){
        MobileElement element = findElement(by);
        int pointX = element.getLocation().getX();
        int pointY = element.getLocation().getY();
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(pointX, pointY)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).
                moveTo(PointOption.point((int)(pointX*x), (int)(pointY*y))).release().perform();
    }

    //坐标定位
    public void tap(int a, int b){
        TouchAction action = new TouchAction(driver);
        PointOption pointOption = new PointOption();
        pointOption.withCoordinates(a, b);
        action.tap(pointOption).release().perform();
    }

    public String getAttribute(By by){
         return findElement(by).getAttribute("content-desc");
    }

    public void quit(){
        driver.quit();
    }


}
