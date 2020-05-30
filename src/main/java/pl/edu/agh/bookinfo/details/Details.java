package pl.edu.agh.bookinfo.details;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Details {

  private @Id @GeneratedValue Long id;
  private String author;
  private int year;
  private String type;
  private int pages;
  private String publisher;
  private String language;

  Details() {
  }

  public Details(String author, int year, String type, int pages, String publisher,
      String language) {
    this.author = author;
    this.year = year;
    this.type = type;
    this.pages = pages;
    this.publisher = publisher;
    this.language = language;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getYear() {
    return this.year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Details))
      return false;

    Details details = (Details) o;

    return Objects.equals(this.id, details.id)
      && Objects.equals(this.author, details.author)
      && Objects.equals(this.year, details.year)
      && Objects.equals(this.type, details.type)
      && Objects.equals(this.pages, details.pages)
      && Objects.equals(this.publisher, details.publisher)
      && Objects.equals(this.language, details.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.author, this.year, this.type, this.pages, this.publisher, this.language);
  }

  @Override
  public String toString() {
    return "Details{id=" + this.id + ", author='" + this.author
      + "', year=" + this.year + ", type=" + this.type
      + "', pages=" + this.pages + ", publisher=" + this.publisher
      + "', language=" + this.language + "'}";
  }
}
