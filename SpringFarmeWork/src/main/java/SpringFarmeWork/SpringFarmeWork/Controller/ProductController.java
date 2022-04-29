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
        return foundProduct.isPresent() ? // true thực hiện dòng trên false chạy dòng dưới
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("False", "Cannot find product with id = " + id, "")
        );
    }

//    INSERT
//    POST MAN: RAW, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        //Check 2 sản phẩm cùng tên
        List<Product> foundProduct = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProduct.size() > 0){
            // Tìm thấy thì báo lỗi
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Product name already taken", "")
            );
        }

        //Insert nếu không có gì xảy ra
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("OK", "Insert product successfully", repository.save(newProduct))
        );
    }


    //Update, upsert = update if not found --> Insert: Tìm thấy thì update, không thấy thì insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        // Tìm kiếm xem id có trong danh sách hay kh
        Product updateProduct = repository.findById(id).map(product -> {
            // Nếu thấy thì ánh xạ và thực hiện thêm thông tin mới
            product.setProductName(newProduct.getProductName());
            product.setYear(newProduct.getYear());
            product.setPrice(newProduct.getPrice());
            return repository.save(product);
        }).orElseGet(() -> {
            // Nếu kh tìm thấy thì insert
            newProduct.setId(id);
            return repository.save(newProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Update Product Successfully", updateProduct)
        );
    }

}
