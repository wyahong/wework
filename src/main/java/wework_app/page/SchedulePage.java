package wework_app.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import wework_app.base.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class SchedulePage extends BasePage {

    public SchedulePage(){}

    /**
     * 创建日程
     * @param day  日期
     * @param mode 日程创建方式： 1-底部的新建日程按钮， 2-右上角的+
     * @param title 日程的主题
     * @return
     */
    public SchedulePage addSchedule(String day, int mode, String title, boolean time, boolean join, boolean more) {
        click(day);
        if(mode==1){
            click("新建日程...");
        }else if(mode==2){
            click(By.id("gym"));
        }else{
            System.out.println("类型错误，请选择 1 (底部新建按钮) 或 2 (右上角+方法新建)");
            return this;
        }
        sendKeys(By.xpath("//android.widget.EditText"), title);
        //可选项
        if(time == true){
            time(Integer.parseInt(day));
        }
        if(join == true){
            join();
        }
        if(more == true){
            more();
        }
        click("保存");
        return this;
    }

    /**
     * @param day ： 选择的日期， 点击时元素为日期+1
     */
    public void time(int day){
        click(By.xpath("//android.widget.LinearLayout[3]/android.widget.ImageView"));
        click(By.id("afk"));
        click(By.xpath("//android.widget.GridView/android.view.View["+(day+1)+"]"));
        click(By.id("bq9"));
        click(By.id("afl"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swipe(By.id("czy"),1, 0.5);
        swipe(By.id("e8k"),1, 0.5);
        click(By.id("bq9"));
        click(By.id("af8"));
        click(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[4]/android.widget.RelativeLayout"));
    }
    //参与人
    public void join(){
        click(By.id("avv"));
        click("企业通讯录");
        click(By.xpath("//android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView"));
        click(By.id("fq1"));
    }
    //更多信息
    public void more(){
        click("更多信息");
        //附件
        click("添加附件...");
        click("选择照片/视频");
        click("从相册选择");
        click(By.id("fqt"));
        click(By.id("gyt"));
        //提醒
        click(By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.TextView"));
        click(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[5]/android.widget.RelativeLayout"));
        //是否重复
        click(By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout[3]/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.TextView"));
        click(By.xpath("//android.widget.RelativeLayout[4]/android.widget.RelativeLayout"));
        //备注
        click("地点");
        sendKeys(byText("地点"), "test");
        click("备注");
        sendKeys(byText("备注"), "test");
    }

    //获取一天的行程
    public List<String> getSchedule(String day){
        if(day != null) {
            click(day);
        }
        return getElements(By.id("gg_")).stream().map(MobileElement::getText).collect(Collectors.toList());
    }

    //删除行程
    public SchedulePage deleteSchedule(String day, String content){
        click(day);
        if(getSchedule(day).contains(content)){
            click(content);
            click(By.id("bfi"));
            click("删除");
        }
        return this;
    }
}
