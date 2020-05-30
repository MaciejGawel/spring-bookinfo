package pl.edu.agh.bookinfo.details;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class DetailsModelAssembler implements RepresentationModelAssembler<Details, EntityModel<Details>> {

  @Override
  public EntityModel<Details> toModel(Details details) {

    return EntityModel.of(details,
      linkTo(methodOn(DetailsController.class).one(details.getId())).withSelfRel(),
      linkTo(methodOn(DetailsController.class).all()).withRel("details"));
  }
}
