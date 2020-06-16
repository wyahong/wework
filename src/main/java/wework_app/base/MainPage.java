package wework_app.base;

import org.openqa.selenium.By;
import wework_app.page.ReportPage;
import wework_app.page.SchedulePage;
import wework_app.page.TodoListPage;

public class MainPage extends BasePage{

    public MainPage() {}

    public SchedulePage toSchedule(){
        click(By.id("adv"));
        return new SchedulePage();
    }

    public TodoListPage todoList(){
        click(By.id("gwu"));
        return new TodoListPage();
    }

    public ReportPage toReport(){
        click(By.xpath("//android.view.ViewGroup/android.widget.RelativeLayout[3]"));
        swipe(1,0.8, 0.2);
        //新版汇报换位置了
        //click(By.xpath("//android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[16]"));
        click(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[12]/android.widget.LinearLayout"));
        return new ReportPage();
    }
}
