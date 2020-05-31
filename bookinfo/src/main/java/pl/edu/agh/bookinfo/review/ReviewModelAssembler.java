package pl.edu.agh.bookinfo.review;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

  @Override
  public EntityModel<Review> toModel(Review review) {

    return EntityModel.of(review,
      linkTo(methodOn(ReviewController.class).one(review.getId())).withSelfRel(),
      linkTo(methodOn(ReviewController.class).all()).withRel("reviews"));
  }
}
