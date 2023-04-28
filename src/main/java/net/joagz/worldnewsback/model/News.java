package net.joagz.worldnewsback.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("news")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News {

  public News(String _id, String title, Instant release_date, String content, String short_description) {
    super();
    this._id = _id;
    this.title = title;
    this.content = content;
    this.short_description = short_description;
  }

  @Id
  private String _id;
  private String title;
  private String content;
  private String short_description;
  private Instant release_date;

  @DBRef
  private List<NewImage> images;

}
