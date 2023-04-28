package net.joagz.worldnewsback.service;

import java.util.List;

import net.joagz.worldnewsback.model.News;

public interface NewsService {

  News findById(String _id);

  List<News> findAll();

  List<News> findByTitle(String title);

  List<News> findByCreation_date(String creation_date);

  List<News> findByShort_description(String short_description);

  News save(News to_save);

  void delete(News to_delete);

}
