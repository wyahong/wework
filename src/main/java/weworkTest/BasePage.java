package weworkTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class BasePage {

    RemoteWebDriver driver;
    WebDriverWait wait;

    public BasePage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }
    public BasePage(RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }
    public void login(){
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/cookie.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                String name = st.nextToken();
                String value = st.nextToken();
                String domain = st.nextToken();
                String path = st.nextToken();
                String expiry_str = st.nextToken();
                Date expiry = null;
                if (!expiry_str.equals("null")) {
                    //cookie : Mon Jan 18 08:00:00 CST 2038
                    SimpleDateFormat sd = new SimpleDateFormat(
                            "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
                    expiry = sd.parse(expiry_str);
                }
                boolean isSecure = Boolean.parseBoolean(st.nextToken());

                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void click(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public String getText(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    public void reFlesh(){
        driver.navigate().refresh();
    }

    public void quit(){
        driver.quit();
    }

}
