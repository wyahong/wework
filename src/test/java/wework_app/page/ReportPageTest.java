package wework_app.page;

import org.junit.jupiter.api.*;
import wework_app.base.MainPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReportPageTest {

    static ReportPage reportPage;

    @BeforeAll
    static void setUp() {
        reportPage = new MainPage().toReport();
    }

    @Test
    @Order(1)
    void createReport() {
        reportPage.createReport("周报", "week1", "week2","week3", false, false,false);
    }

    @Test
    @Order(2)
    void getDetail(){
        System.out.println(reportPage.getReportDetail());;
    }

    @Test
    @Order(3)
    void comment(){
        reportPage.comment("comment");
    }

    @Test
    @Order(4)
    void delete(){
        reportPage.delReport();
    }

    @AfterAll
    static void tearDown() {
        reportPage.quit();
    }
}