package SpringFarmeWork.SpringFarmeWork.Controller;

import SpringFarmeWork.SpringFarmeWork.Model.Product;
import SpringFarmeWork.SpringFarmeWork.Model.ResponseObject;
import SpringFarmeWork.SpringFarmeWork.Reponsitories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {

    @Autowired
    private ProductRepository repository; // Đối tượng reponsitory sẽ được tạo ra khi chạy (Tạo 1 lần sau dùng tiếp)

    //Method
    @GetMapping("")
    List<Product> getAllProducts(){
        return repository.findAll(); // gọi đến findAll()
    }

    //Get Detail Product
    @GetMapping("/{id}")
    //Show message báo lỗi chuẩn
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("OK", "Query product successfully", foundProduct)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("False", "Cannot find product with id = " + id, "")
            );
        }
    }
}
