package pl.edu.agh.bookinfo.product;

class ProductNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ProductNotFoundException(Long id) {
    super("Could not find product " + id);
  }
}
