package pl.edu.agh.bookinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.agh.bookinfo.details.Details;
import pl.edu.agh.bookinfo.details.DetailsRepository;
import pl.edu.agh.bookinfo.product.Product;
import pl.edu.agh.bookinfo.product.ProductRepository;
import pl.edu.agh.bookinfo.review.Review;
import pl.edu.agh.bookinfo.review.ReviewRepository;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(ProductRepository productRepository, DetailsRepository detailsRepository,
      ReviewRepository reviewRepository) {
    return args -> {
      productRepository.save(new Product("978-3-16-148410-0", "The Comedy of Errors", "The Comedy of Errors is one of William Shakespeare early plays."));
      productRepository.findAll().forEach(product -> log.info("Preloaded " + product));

      detailsRepository.save(new Details("978-3-16-148410-0", "William Shakespeare", 1595, "paperback", 200, "PublisherA", "English"));
      detailsRepository.findAll().forEach(details -> log.info("Preloaded " + details));

      reviewRepository.save(new Review("78-3-16-148410-0", "Reviewer1", "An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!"));
      reviewRepository.findAll().forEach(reviews -> log.info("Preloaded " + reviews));
    };
  }
}
