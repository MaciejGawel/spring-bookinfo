package pl.edu.agh.bookinfo.review;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

  private final ReviewRepository repository;
  private final ReviewModelAssembler assembler;

  ReviewController(ReviewRepository repository, ReviewModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Collection

  @GetMapping("/reviews")
  CollectionModel<EntityModel<Review>> all() {

    List<EntityModel<Review>> reviews = repository.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());

    return CollectionModel.of(reviews, linkTo(methodOn(ReviewController.class).all()).withSelfRel());
  }

  @PostMapping("/reviews")
  ResponseEntity<?> newReview(@RequestBody Review newReview) {

    EntityModel<Review> entityModel = assembler.toModel(repository.save(newReview));

    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  // Single item

  @GetMapping("/reviews/{id}")
  EntityModel<Review> one(@PathVariable Long id) {

    Review review = repository.findById(id)
      .orElseThrow(() -> new ReviewNotFoundException(id));

    return assembler.toModel(review);
  }

  @PutMapping("/reviews/{id}")
  ResponseEntity<?> replaceReview(@RequestBody Review newReview, @PathVariable Long id) {

    Review updatedReview = repository.findById(id)
        .map(review -> {
          review.setIsbn(newReview.getIsbn());
          review.setReviewer(newReview.getReviewer());
          review.setText(newReview.getText());
          return repository.save(review);
        })
        .orElseGet(() -> {
          newReview.setId(id);
          return repository.save(newReview);
        });

    EntityModel<Review> entityModel = assembler.toModel(updatedReview);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/reviews/{id}")
  ResponseEntity<?> deleteReview(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
