//package pl.agh.student.pcmz.pracainzynierska;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.agh.student.pcmz.pracainzynierska.models.Book;
//import pl.agh.student.pcmz.pracainzynierska.services.BookService;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//public class BookServiceUnitTest {
//
//    @Autowired
//    private BookService bookService;
//
//    @Test
//    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
//        List<Book> books = bookService.list();
//
//        Assert.assertEquals(books.size(), 3);
//    }
//}
