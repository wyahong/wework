package weworkTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * 通讯录
 */
public class ContactPage extends BasePage{

    //确认按钮
    By submit =By.xpath("//a[@d_ck=\"submit\"]");

    public ContactPage(RemoteWebDriver driver) {
       super(driver);
    }

    //创建部门
    public ContactPage addDepartment(String department){
        click(By.xpath("//i[@class='member_colLeft_top_addBtn']"));
        click(By.className("js_create_party"));
        sendKeys(By.xpath("//input[@class='qui_inputText ww_inputText'][1]"), department);
        click(By.xpath("//a[@class=\"qui_btn ww_btn ww_btn_Dropdown js_toggle_party_list\"]"));
        click(By.xpath("(//a[@id=\"1688852698251634_anchor\"])[2]"));
        click(submit);

        return this;
    }

    //删除部门
    public ContactPage delDepartment(String department){
        click(By.xpath("//a[text()=\""+ department + "\"]"));
        click(By.xpath("//a[text()=\""+ department + "\"]/span"));
        click(By.xpath("//a[@rel=\"3\"]"));
        click(submit);

        return this;
    }

    //搜索
    public ContactPage search(String content){
        sendKeys(By.id("memberSearchInput"), content);
        return this;
    }

    //获取部门名称
//    public String getName(String party_name){
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("party_name"), party_name));
//        return getText(By.id("party_name"));
//    }

    //添加默认标签
    public ContactPage addTag(String tag){
        click(By.xpath("//a[@href=\"#contacts/tag\"]/.."));
        click(By.xpath("//a[@on-click=\"createTag\"]"));
        sendKeys(By.xpath("//input[@name=\"name\"]"), tag);
        click(submit);

        return this;
    }

    //添加指定管理员标签
    public ContactPage addTag2(String tag, String managerName){
        click(By.xpath("//a[@on-click=\"createTag\"]"));
        sendKeys(By.xpath("//input[@name=\"name\"]"), tag);
        click(By.xpath("//a[@class=\"qui_btn ww_btn ww_btn_Dropdown js_toggle_share_range\"]"));
        click(By.xpath("//a[@on-click=\"setShareRange\"]"));
        sendKeys(By.xpath("//input[@id=\"memberSearchInput\"]"),managerName);
        click(By.xpath("(//li[@data-type=\"member\"])[1]"));
        click(By.xpath("(//a[@d_ck=\"submit\"])[2]"));
        click(By.xpath("(//a[@d_ck=\"submit\"])[1]"));

        return this;
    }

    //删除标签
    public ContactPage delTag(String tag){
        click(By.xpath("//li[text()=\""+ tag + " \"]/a"));
        click(By.xpath("//a[@on-click=\"removeTag\"]"));
        click(By.xpath("//a[@node-type=\"ok\"]"));

        return  this;
    }

    //获取成功提示
    public String getTips(){
        String tips = getText(By.id("js_tips"));
        return tips;
    }
}
