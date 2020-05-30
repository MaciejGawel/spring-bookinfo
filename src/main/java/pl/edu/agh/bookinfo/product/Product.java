package pl.edu.agh.bookinfo.product;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

  private @Id @GeneratedValue Long id;
  private String isbn;
  private String title;
  private String description;

  Product() {}

  public Product(String isbn, String title, String description) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return this.isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Product))
      return false;

    Product product = (Product) o;

    return Objects.equals(this.id, product.id)
      && Objects.equals(this.isbn, product.isbn)
      && Objects.equals(this.title, product.title)
      && Objects.equals(this.description, product.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.isbn, this.title, this.description);
  }

  @Override
  public String toString() {
    return "Product{id=" + this.id + ", isbn='" + this.isbn
      + ", title='" + this.title
      + "', description='" + this.description + "'}";
  }
}
