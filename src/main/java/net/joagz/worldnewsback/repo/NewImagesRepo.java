package net.joagz.worldnewsback.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.joagz.worldnewsback.model.NewImage;

public interface NewImagesRepo extends MongoRepository<NewImage, String> {
  
}
