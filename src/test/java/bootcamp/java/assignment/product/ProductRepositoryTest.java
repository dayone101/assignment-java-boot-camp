package bootcamp.java.assignment.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameContainingIgnoreCase() {
        // Arrange
        Product product1 = new Product();
        product1.setName("ZXCVBN");
        product1.setPrice(100);

        Product product2 = new Product();
        product2.setName("ZxcVbn");
        product2.setPrice(200);

        Product product3 = new Product();
        product3.setName("ASDFG");
        product3.setPrice(300);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // Act
        List<Product> result =
                productRepository.findByNameContainingIgnoreCase("zxcvbn");

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void findById_found() {
        // Arrange
        Product product = new Product();
        product.setName("ZXC");
        product.setPrice(100);

        productRepository.save(product);

        // Act
        Optional<Product> result =
                productRepository.findById(product.getId());

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void findById_not_found() {
        // Act
        Optional<Product> result =
                productRepository.findById(-1);

        // Assert
        assertFalse(result.isPresent());
    }
}