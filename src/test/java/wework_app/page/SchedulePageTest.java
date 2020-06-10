package wework_app.page;

import org.junit.jupiter.api.*;
import wework_app.base.MainPage;

class SchedulePageTest {

    static SchedulePage schedulePage;

    @BeforeAll
    static void setUp() {
        schedulePage = new MainPage().toSchedule();
    }

    @Test
    void addSchedule() {
        String day = "15";
        String title = "schedule15";
        schedulePage.addSchedule(day,2, title,false,false,false);
    }

    @Test
    void deleteSchedule(){
        schedulePage.deleteSchedule("15", "schedule15");
    }

    @AfterAll
    static void tearDown() {
        schedulePage.quit();
    }
}