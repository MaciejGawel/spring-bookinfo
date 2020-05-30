package pl.edu.agh.bookinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.agh.bookinfo.product.Product;
import pl.edu.agh.bookinfo.product.ProductRepository;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository productRepository) {
    return args -> {
      productRepository.save(new Product("The Comedy of Errors", "The Comedy of Errors is one of William Shakespeare early plays."));
      productRepository.findAll().forEach(product -> log.info("Preloaded " + product));
    };
  }
}
