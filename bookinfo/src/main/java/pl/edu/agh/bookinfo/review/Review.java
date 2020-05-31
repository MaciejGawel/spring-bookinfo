package pl.edu.agh.bookinfo.review;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {

  private @Id @GeneratedValue Long id;
  private String isbn;
  private String reviewer;
  private String text;

  Review() {}

  public Review(String isbn, String reviewer, String text) {
    this.isbn = isbn;
    this.reviewer = reviewer;
    this.text = text;
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

  public String getReviewer() {
    return this.reviewer;
  }

  public void setReviewer(String reviewer) {
    this.reviewer = reviewer;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Review))
      return false;

    Review review = (Review) o;

    return Objects.equals(this.id, review.id)
      && Objects.equals(this.isbn, review.isbn)
      && Objects.equals(this.reviewer, review.reviewer)
      && Objects.equals(this.text, review.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.isbn, this.reviewer, this.text);
  }

  @Override
  public String toString() {
    return "Review{id=" + this.id + ", isbn='" + this.isbn
      + ", reviewer='" + this.reviewer
      + "', text='" + this.text + "'}";
  }
}
