package edu.school21.repositories;

import edu.school21.models.Product;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsReposutoryJdbcImplTest {
    private ProductsRepository pr;
    private EmbeddedDatabase dataSource;

    final List<Product> FIND_ALL = Arrays.asList(
        new Product(1l, "product1", 100),
        new Product(2l, "product2", 1000),
        new Product(3l, "product3", 10000),
        new Product(4l, "product4", 100000),
        new Product(5l, "product5", 1000000));

    final Product FIND_BY_ID = new Product(4l, "product4", 100000);
    final Product UPDATED = new Product(5l, "product6", 100);
    final Product SAVE = new Product(6l, "product7", 1000);

    final List<Product> AFTER_DELETE = Arrays.asList(
            new Product(1l, "product1", 100),
            new Product(3l, "product3", 10000),
            new Product(4l, "product4", 100000),
            new Product(5l, "product5", 1000000));

    @BeforeEach
    private void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        pr = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void testFindAll() {
        List<Product> actual = pr.findAll();
        Assertions.assertEquals(FIND_ALL, actual);
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(FIND_BY_ID, pr.findById(4l).get());
        Assertions.assertEquals(Optional.empty(), pr.findById(10l));
        Assertions.assertEquals(Optional.empty(), pr.findById(null));
    }

    @Test
    public void testUpdate() {
        pr.update(new Product(5l, "product6", 100));
        Assertions.assertEquals(UPDATED, pr.findById(5l).get());
    }

    @Test
    public void testSave() {
        pr.save(new Product(6l, "product7", 1000));
        Assertions.assertEquals(SAVE, pr.findById(6l).get());
    }

    @Test
    public void testDelete() {
        pr.delete(2l);
        Assertions.assertEquals(AFTER_DELETE, pr.findAll());
        Assertions.assertFalse(pr.findById(2l).isPresent());
    }

    @AfterEach
    public void close() {
        dataSource.shutdown();
    }
}