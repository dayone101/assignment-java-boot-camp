package bootcamp.java.assignment.product;

import bootcamp.java.assignment.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void findById_found() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("zxcvbn");
        product.setPrice(100);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        Product result = testRestTemplate.getForObject("/product/1", Product.class);

        // Assert
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void findById_not_found() {
        // Act
        String result = testRestTemplate.getForObject("/product/1", String.class);

        // Assert
        assertEquals("product id 1 not found", result);
    }
}