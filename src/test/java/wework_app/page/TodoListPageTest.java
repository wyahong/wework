package wework_app.page;

import org.junit.jupiter.api.*;
import wework_app.base.MainPage;

import static org.junit.jupiter.api.Assertions.*;

class TodoListPageTest {

    static TodoListPage todoListPage;

    @BeforeAll
    static void setUp() {
        todoListPage = new MainPage().todoList();
    }

    @Test
    void createItem() {
        String content = "todo222";
        todoListPage.createItem(2, content, false, true, 23);
        assertEquals(content,todoListPage.getList().get(0));
    }
    @Test
    void done() {
       todoListPage.done();
    }

    @Test
    void getItem(){
        todoListPage.getDone();
        todoListPage.getDone();
    }

    @Test
    void deleteItem(){
        todoListPage.deleteItem();
    }

    @AfterAll
    static void tearDown() {
        todoListPage.quit();
    }

}