package wework_app.page;

import org.openqa.selenium.By;
import wework_app.base.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇报页
 * 新建：日报、周报、月报
 */
public class ReportPage extends BasePage {

    public ReportPage() {
    }

    /**
     * 新建：日报/周报/月报
     * @param type : 创建的类型 - 日报、周报、月报
     * @param work： 今日工作/本周工作/本月工作
     * @param plan： 明日计划/下周计划/下月计划
     * @param other：其他事项
     * @return
     */
    public ReportPage createReport(String type, String work, String plan, String other,boolean attachment, boolean report, boolean send){
        //打开日报编辑页
        if(type.equals("日报") || type.equals("周报") || type.equals("月报")){
            click(type);
        }else{
            System.out.println("创建的类型错误，请选择：日报 |周报 | 月报");
            return this;
        }
        //内容编辑
        click(By.xpath("//android.webkit.WebView[@content-desc=\""+type+"\"]/android.widget.EditText[1]"));
        sendKeys(By.xpath("//android.webkit.WebView[@content-desc=\""+type+"\"]/android.widget.EditText[1]"), work);
        click(By.xpath("//android.webkit.WebView[@content-desc=\""+type+"\"]/android.widget.EditText[2]"));
        sendKeys(By.xpath("//android.webkit.WebView[@content-desc=\""+type+"\"]/android.widget.EditText[2]"), plan);
        click(By.xpath("//android.webkit.WebView[@content-desc=\""+type+"\"]/android.widget.EditText[3]"));
        sendKeys(By.xpath("//android.webkit.WebView[@content-desc=\""+type+"\"]/android.widget.EditText[3]"), other);
        //滑动展示底部元素
        swipe(1,0.8, 0.2);
        //可选项
        if(attachment == true)
            upLoadAttachment();
        if(report == true)
            reportTo(type);
        if(send == true)
            send(type);
        //提交
        click(By.xpath("//android.view.View[@content-desc=\"提交\"]"));
        return this;
    }
    //附件
    public void upLoadAttachment(){
        tap(60, 1065);
        if(isExist(By.id("gym"))){
            click("删除");
            click("确定");
            tap(60, 1065);
        }
        click("从相册选择");
        click(By.id("fqt"));
        click(By.id("gyt"));
    }
    //汇报给
    public void reportTo(String type){
        click(By.xpath("//android.webkit.WebView[@content-desc='"+type+"']/*[8]"));
        click("企业通讯录");
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView"));
        click(By.id("fq1"));
    }
    //发送到
    public void send(String type){
        click(By.xpath("//android.webkit.WebView[@content-desc='"+type+"']/*[10]"));
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView"));
        click(By.id("fq1"));
    }

    //详情页数据
    public List<String> getReportDetail(){
        List<String> reportDetail = new ArrayList<>();
        reportDetail.add(getAttribute(By.xpath("//android.webkit.WebView[@content-desc=\"企业微信汇报\"]/*[3]")));
        reportDetail.add(getAttribute(By.xpath("//android.webkit.WebView[@content-desc=\"企业微信汇报\"]/*[5]")));
        reportDetail.add(getAttribute(By.xpath("//android.webkit.WebView[@content-desc=\"企业微信汇报\"]/*[7]")));
        reportDetail.add(getAttribute(By.xpath("//android.webkit.WebView[@content-desc=\"企业微信汇报\"]/*[9]")));
        return reportDetail;
    }

    //详情页删除
    public ReportPage delReport(){
        click(By.id("gym"));
        click(By.id("b3g"), 2);
        click("确定");
        return this;
    }

    //详情页评论
    public ReportPage comment(String comment){
        click(By.id("bu1"));
        sendKeys(By.id("btw"), comment);
        click(By.id("bwu"));
        click("[得意]");
        click(By.id("fta"));
        return this;
    }

    public ReportPage backToReport(){
        //返回汇报主页
        click("返回按钮");
        return this;
    }

}
