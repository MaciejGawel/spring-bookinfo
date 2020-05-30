package pl.edu.agh.bookinfo.product;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

  private @Id @GeneratedValue Long id;
  private String title;
  private String description;

  Product() {}

  public Product(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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
      && Objects.equals(this.title, product.title)
      && Objects.equals(this.description, product.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.title, this.description);
  }

  @Override
  public String toString() {
    return "Product{id=" + this.id + ", title='" + this.title
      + "', description='" + this.description + "'}";
  }
}
