package net.joagz.worldnewsback.dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.internal.connection.Time;

import lombok.AllArgsConstructor;
import net.joagz.worldnewsback.model.NewImage;
import net.joagz.worldnewsback.model.News;
import net.joagz.worldnewsback.repo.NewImagesRepo;
import net.joagz.worldnewsback.service.IFileUploadService;

@Service
@AllArgsConstructor
public class FileUploadServiceImpl implements IFileUploadService {

  @Autowired
  private final NewsDao newsDao;

  @Autowired
  private final NewImagesRepo imagesRepo;

  private final Path rootFolder = Paths.get("src/main/resources/uploads");

  @Override
  public void save(MultipartFile file) throws Exception {
  }

  @Override
  public void save(List<MultipartFile> files, News newToUpdate) throws Exception {
    List<NewImage> images = new LinkedList<>();

    for (MultipartFile file : files) {
      String name = Time.nanoTime() + file.getOriginalFilename();

      images.add(imagesRepo
          .save(new NewImage(null,
              name, file.getSize(),
              "src/main/resources/uploads/" + name)));

      Files.copy(file.getInputStream(), this.rootFolder.resolve(name));
    }

    newToUpdate.setImages(images);
    newsDao.save(newToUpdate);

  }

  @Override
  public NewImage findById(String id) {

    return imagesRepo.findById(id).get();

  }

}
