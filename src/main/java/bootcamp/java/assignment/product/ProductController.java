package bootcamp.java.assignment.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{name}")
    public List<Product> findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findById(id);
    }
}
