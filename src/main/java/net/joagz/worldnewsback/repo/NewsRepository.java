package net.joagz.worldnewsback.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import net.joagz.worldnewsback.model.News;

public interface NewsRepository extends MongoRepository<News, String> {

  @Query(value = "db.users.find({'title': /.*$0.*/})")
  public List<News> searchByTitle(String title);

  @Query(value = "db.users.find({'short_description': /.*$0.*/})")
  public List<News> searchByShort_description(String short_description);

  @Query(value = "db.users.find({'release_date': /.*$0.*/})")
  public List<News> searchByDate(String release_date);

}
