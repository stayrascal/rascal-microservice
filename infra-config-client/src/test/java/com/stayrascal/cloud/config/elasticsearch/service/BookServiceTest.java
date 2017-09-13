package com.stayrascal.cloud.config.elasticsearch.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.stayrascal.cloud.config.InfraConfigClientApplication;
import com.stayrascal.cloud.config.elasticsearch.model.Book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InfraConfigClientApplication.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before(){
        esTemplate.deleteIndex(Book.class);
        esTemplate.createIndex(Book.class);
        esTemplate.putMapping(Book.class);
        esTemplate.refresh(Book.class);
    }

    @Test
    public void testSave() throws Exception {
        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        Book testBook = bookService.save(book);
        assertNotNull(testBook.getId());
        assertEquals(testBook.getTitle(), book.getTitle());
    }

    @Test
    public void testFindOne() throws Exception {
        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);

        Book testBook = bookService.findOne(book.getId());
        assertNotNull(testBook.getId());

    }
}