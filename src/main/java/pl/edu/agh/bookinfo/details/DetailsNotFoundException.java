package pl.edu.agh.bookinfo.details;

class DetailsNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  DetailsNotFoundException(Long id) {
    super("Could not find deatils " + id);
  }
}
