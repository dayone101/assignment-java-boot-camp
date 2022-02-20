package bootcamp.java.assignment;

import bootcamp.java.assignment.product.Product;
import bootcamp.java.assignment.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ShoppingApiApplication {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void initialData() {
		initialProduct();
	}

	@Transactional
	private void initialProduct() {
		Product product1 = new Product();
		product1.setName("horse red pen");
		product1.setPrice(5);
		product1.setDetail("red pen from horse");

		Product product2 = new Product();
		product2.setName("horse blue pen");
		product2.setPrice(5);
		product2.setDetail("blue pen from horse");

		Product product3 = new Product();
		product3.setName("horse black pen");
		product3.setPrice(5);
		product3.setDetail("black pen from horse");

		Product product4 = new Product();
		product4.setName("rotring black pen");
		product4.setPrice(500);
		product4.setDetail("luxury black pen from rotring");

		Product product5 = new Product();
		product5.setName("rotring mechanical pencil");
		product5.setPrice(200);
		product5.setDetail("mechanical pencil from rotring");

		Product product6 = new Product();
		product6.setName("faber-castell eraser");
		product6.setPrice(15);
		product6.setDetail("eraser from faber-castell");

		Product product7 = new Product();
		product7.setName("no brand pencil");
		product7.setPrice(5);
		product7.setDetail("normal no brand pencil");

		Product product8 = new Product();
		product8.setName("mechanical pencil from pentel");
		product8.setPrice(30);
		product8.setDetail("mechanical pencil from pentel");

		Product product9 = new Product();
		product9.setName("no brand ruler");
		product9.setPrice(5);
		product9.setDetail("normal no brand ruler");

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);
		productRepository.save(product5);
		productRepository.save(product6);
		productRepository.save(product7);
		productRepository.save(product8);
		productRepository.save(product9);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApiApplication.class, args);
	}

}
