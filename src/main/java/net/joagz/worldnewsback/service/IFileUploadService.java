package net.joagz.worldnewsback.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import net.joagz.worldnewsback.model.NewImage;
import net.joagz.worldnewsback.model.News;

public interface IFileUploadService {
  public void save(MultipartFile file) throws Exception;

  public void save(List<MultipartFile> files, News newToUpdate) throws Exception;

  public NewImage findById(String id);

}
