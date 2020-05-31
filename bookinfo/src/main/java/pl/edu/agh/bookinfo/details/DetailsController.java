package pl.edu.agh.bookinfo.details;

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
public class DetailsController {

  private final DetailsRepository repository;
  private final DetailsModelAssembler assembler;

  DetailsController(DetailsRepository repository, DetailsModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Collection

  @GetMapping("/details")
  CollectionModel<EntityModel<Details>> all() {

    List<EntityModel<Details>> details = repository.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());

    return CollectionModel.of(details, linkTo(methodOn(DetailsController.class).all()).withSelfRel());
  }

  @PostMapping("/details")
  ResponseEntity<?> newDetails(@RequestBody Details newDetails) {

    EntityModel<Details> entityModel = assembler.toModel(repository.save(newDetails));

    return ResponseEntity
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
      .body(entityModel);
  }

  // Single item

  @GetMapping("/details/{id}")
  EntityModel<Details> one(@PathVariable Long id) {

    Details details = repository.findById(id)
      .orElseThrow(() -> new DetailsNotFoundException(id));

    return assembler.toModel(details);
  }

  @PutMapping("/details/{id}")
  ResponseEntity<?> replaceDetails(@RequestBody Details newDetails, @PathVariable Long id) {

    Details updatedDetails = repository.findById(id)
        .map(details -> {
          details.setIsbn(newDetails.getIsbn());
          details.setAuthor(newDetails.getAuthor());
          details.setYear(newDetails.getYear());
          details.setType(newDetails.getType());
          details.setPages(newDetails.getPages());
          details.setPublisher(newDetails.getPublisher());
          details.setLanguage(newDetails.getLanguage());
          return repository.save(details);
        })
        .orElseGet(() -> {
          newDetails.setId(id);
          return repository.save(newDetails);
        });

    EntityModel<Details> entityModel = assembler.toModel(updatedDetails);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/details/{id}")
  ResponseEntity<?> deleteDetails(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
