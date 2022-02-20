package bootcamp.java.assignment.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void findById() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("zxcvbn");
        product.setPrice(100);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        ProductService productService = new ProductService();
        productService.setProductRepository(productRepository);
        Product result = productService.findById(1);

        // Assert
        assertEquals(product.getName(), result.getName());
    }
}