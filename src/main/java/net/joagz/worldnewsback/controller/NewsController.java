package net.joagz.worldnewsback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.joagz.worldnewsback.dao.NewsDao;
import net.joagz.worldnewsback.model.News;
import net.joagz.worldnewsback.repo.NewImagesRepo;
import net.joagz.worldnewsback.repo.NewsRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
@CrossOrigin("*")
public class NewsController {

  @Autowired
  private final NewsDao newsDao;
  @Autowired
  private final NewsRepository newsRepository;
  @Autowired
  private final NewImagesRepo imagesRepo;

  @GetMapping("/")
  @CrossOrigin("*")
  public List<News> findAll() {
    return newsDao.findAll();
  }

  @PostMapping("/")
  @CrossOrigin("*")
  public News create(@RequestBody News news) {
    return newsDao.save(news);
  }

  @GetMapping("/{id}")
  @CrossOrigin("*")
  public ResponseEntity<?> findById(@PathVariable String id) {
    if (newsDao.findById(id) != null)
      return new ResponseEntity<News>(newsDao.findById(id), HttpStatus.OK);
    return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/{id}")
  @CrossOrigin("*")
  public ResponseEntity<?> deleteById(@PathVariable String id) {
    if (newsDao.findById(id) != null) {
      News toDelete = newsDao.findById(id);
      System.out.println(toDelete.toString());
      toDelete.getImages().stream().forEach(image -> imagesRepo.deleteById(image.get_id()));
      newsDao.delete(toDelete);
      return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
    return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);
  }

  @PutMapping("/edit/{id}")
  @CrossOrigin("*")
  public ResponseEntity<?> editById(@PathVariable String id, @RequestBody News news) {
    if (newsDao.findById(id) != null) {
      news.set_id(id);
      return new ResponseEntity<News>(newsDao.save(news), HttpStatus.OK);
    }
    return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/all")
  public ResponseEntity<?> deleteAll() {
    newsRepository.deleteAll();
    imagesRepo.deleteAll();
    return ResponseEntity.status(200).body("All Deleted");
  }

}
