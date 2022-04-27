package SpringFarmeWork.SpringFarmeWork.Database;

import SpringFarmeWork.SpringFarmeWork.Model.Product;
import SpringFarmeWork.SpringFarmeWork.Reponsitories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Database {

    //Gọi đến toString hiển thị đối tượng (Thay cho sout) các method có trong class Database
    private static Logger logger = LoggerFactory.getLogger(Database.class);


    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){

        //Insert du lieu dtb
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("MacBook Pro 15", 2020, 2200.00, "");
                Product productB = new Product("Dell Core i7", 2016, 1700.00, "");
                Product productC = new Product("HP LiteBook", 2022, 1800.00, "");

                //Lưu đối tượng vào dtb
                logger.info("insert data: " + productRepository.save(productA));
                logger.info("insert data: " + productRepository.save(productB));
                logger.info("insert data: " + productRepository.save(productC));
            }
        };
    }
}
