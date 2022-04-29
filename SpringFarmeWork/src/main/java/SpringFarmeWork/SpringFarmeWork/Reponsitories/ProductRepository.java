package SpringFarmeWork.SpringFarmeWork.Reponsitories;

import SpringFarmeWork.SpringFarmeWork.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
