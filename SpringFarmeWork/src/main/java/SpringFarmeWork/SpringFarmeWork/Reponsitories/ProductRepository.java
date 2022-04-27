package SpringFarmeWork.SpringFarmeWork.Reponsitories;

import SpringFarmeWork.SpringFarmeWork.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
