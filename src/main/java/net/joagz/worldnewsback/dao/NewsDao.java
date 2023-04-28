package net.joagz.worldnewsback.dao;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.joagz.worldnewsback.model.News;
import net.joagz.worldnewsback.repo.NewsRepository;
import net.joagz.worldnewsback.service.NewsService;

@Service
@AllArgsConstructor
public class NewsDao implements NewsService {

  @Autowired
  private final NewsRepository repo;

  @Override
  public News findById(String _id) {

    Optional<News> found = repo.findById(_id);

    if (found.isPresent())
      return found.get();

    return null;

  }

  @Override
  public List<News> findAll() {
    return repo.findAll();
  }

  @Override
  public List<News> findByTitle(String title) {
    List<News> found = repo.searchByTitle(title);
    return found;
  }

  @Override
  public List<News> findByCreation_date(String creation_date) {
    List<News> found = repo.searchByDate(creation_date);
    return found;
  }

  @Override
  public List<News> findByShort_description(String short_description) {
    List<News> found = repo.searchByShort_description(short_description);
    return found;
  }

  @Override
  public News save(News to_save) {
    Instant date = Instant.now();
    to_save.setRelease_date(date);
    return repo.save(to_save);
  }

  @Override
  public void delete(News to_delete) {
    repo.delete(to_delete);
  }

}
