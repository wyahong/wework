package wework_web;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GetCookie {

    static WebDriver driver;

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);

        GetCookie cookie = new GetCookie();
        cookie.getCookie();
    }

    void getCookie() {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/cookie.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : driver.manage().getCookies()) {
                bufferedWriter.write(
                        cookie.getName() + ";" +
                                cookie.getValue() + ";" +
                                cookie.getDomain() + ";" +
                                cookie.getPath() + ";" +
                                cookie.getExpiry() + ";" +
                                cookie.isSecure()
                );
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}