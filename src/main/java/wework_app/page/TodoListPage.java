package wework_app.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import wework_app.base.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * 代办页
 */
public class TodoListPage extends BasePage {

    public TodoListPage(){}


//    public TodoListPage createItem(int mode, String content) {
//        if(mode==1){
//            swipe(1, 0.5, 0.8);
//        }else if(mode==2){
//            click(By.id("gym"));
//        }else{
//            System.out.println("类型不对，请选择 1(下滑新建) 或 2( +号按钮新建)");
//            return this;
//        }
//        sendKeys(By.xpath("//android.widget.EditText"), content);
//        //参与人
//        click("请选择");
//        click("企业通讯录");
//        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView"));
//        click(By.id("fq1"));
//        //提醒我
//        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.TextView\n"));
//
//        tap(626, 1230);
//        swipe(0.1, 0.2);
//        swipe(0.2, 0.8);
//        click(By.xpath("确定"));
//        //保存
//        click("保存");
//        return this;
//    }
    /**
     * 新建代办事项
     * @param mode :  1-下滑新建   2-点击+新建待办事项
     * @param content : 代办事项内容
     * @param date    : 提醒日期
     * @return
     */
    public TodoListPage createItem(int mode, String content, boolean join, boolean notice, int date) {
        if(mode==1){
            swipe(1,0.5, 0.8);
        }else if(mode==2){
            click(By.id("gym"));
        }else{
            System.out.println("类型不对，请选择 1(下滑新建) 或 2( +号按钮新建)");
            return this;
        }
        sendKeys(By.xpath("//android.widget.EditText"), content);
        //选填项
        if(join==true){
            join();
        }
        if(notice==true){
            notice(date);
        }
        //保存
        click("保存");
        return this;
    }
    //参与人
    private void join(){
        click("请选择");
        click("企业通讯录");
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView"));
        click(By.id("fq1"));
    }
    //提醒我
    public void notice(int date){
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.TextView\n"));
        if(isExist(By.xpath("//android.widget.GridView/android.view.View["+(date+1)+"]"))){
            click(By.xpath("//android.widget.GridView/android.view.View["+(date+1)+"]"));
            swipe(By.id("czy"),1, 0.5);
            swipe(By.id("e8k"),1, 0.5);
            click(By.id("bq9"));
        }
    }

    //代办列表：主题内容
    public List<String> getList(){
        List<String>  todoList =  new ArrayList<>();
        for(MobileElement e : getElements(By.id("gw9"))){
            todoList.add(e.getText());
        }
        return todoList;
    }

    //完成一个待办事项
    public void done(){
        if(isExist(By.id("gwt"))){
            click(getElements(By.id("gwt")).get(0));
        }else{
            System.out.println("没有代办事项");
            return;
        }
    }

    //点击展开/隐藏已完成事项
    public void getDone(){
        if(isExist(By.id("eps"))){
            click(By.id("eps"));
        }else{
            System.out.println("没有已完成事项");
            return;
        }
    }

    //删除待办事项
    public void deleteItem(){
        if(isExist(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"))){
            swipe(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]"),0.9, 0.5);
            click(getElements(By.id("gwt")).get(0));
        }
    }

    //返回首页
    public void back(){
        click(By.id("gyb"));
    }
}
