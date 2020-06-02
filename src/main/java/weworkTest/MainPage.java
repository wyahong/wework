package weworkTest;

import org.openqa.selenium.By;

/**
 * 主页
 */
public class MainPage extends BasePage{

    public MainPage() {
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
        login();
        reFlesh();
    }

    public ContactPage toContact(){
        click(By.id("menu_contacts"));
        return new ContactPage(driver);
    }
}
