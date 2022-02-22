package bootcamp.java.assignment.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUserId(int userId);

    @Transactional
    void removeByUserId(int userId);

}
