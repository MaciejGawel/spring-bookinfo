package pl.edu.agh.bookinfo.review;

class ReviewNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ReviewNotFoundException(Long id) {
    super("Could not find review " + id);
  }
}
