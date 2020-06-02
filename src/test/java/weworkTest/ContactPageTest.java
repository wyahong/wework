package weworkTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * case
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactPageTest {

    static MainPage main;
    static ContactPage contact;

    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }

    @Test
    @Order(1)
    void testAddDepartment() {
        contact.addDepartment("test33");
        assertEquals("新建部门成功", contact.getTips());
    }

    @Test
    @Order(2)
    void testDelDepartment() {
        contact.delDepartment("test33");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("删除部门成功", contact.getTips());
    }

    @Test
    @Order(3)
    void testAddTag(){
        contact.addTag("tag1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("创建成功", contact.getTips());
    }

    @Test
    @Order(5)
    void testAddTag2(){
        contact.addTag2("tag5", "ya");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("创建成功", contact.getTips());
    }

    @Test
    @Order(4)
    void testDelTag(){
        contact.delTag("tag1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("删除成功", contact.getTips());
    }

    @AfterAll
    static void afterAll(){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        contact.quit();
    }
}