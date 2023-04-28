package net.joagz.worldnewsback.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import net.joagz.worldnewsback.dao.FileUploadServiceImpl;
import net.joagz.worldnewsback.dao.NewsDao;
import net.joagz.worldnewsback.model.NewImage;

@RestController
@RequestMapping("/api/files")
@AllArgsConstructor
public class UploadController {

  @Autowired
  private final FileUploadServiceImpl fileService;

  @Autowired
  private final NewsDao newsDao;

  @PostMapping(path = "/upload/{publication_id}")
  @CrossOrigin("*")
  public ResponseEntity<String> uploadFiles(@RequestBody List<MultipartFile> files,
      @PathVariable("publication_id") String id) {

    System.out.println(files);

    try {
      fileService.save(files, newsDao.findById(id));
      return new ResponseEntity<String>("Files uploaded correctly to server", HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<String>("An error ocurred when uploading the file", HttpStatus.INTERNAL_SERVER_ERROR);

    }
  }

  @ResponseBody
  @RequestMapping(value = "/image/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  @CrossOrigin("*")
  public ResponseEntity<?> load(@PathVariable String id) throws IOException {

    NewImage image = fileService.findById(id);

    try {
      InputStream in = new ClassPathResource("/uploads/" + image.getName()).getInputStream();
      return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
    } catch (FileNotFoundException ex) {
      return new ResponseEntity<String>("Image not found", HttpStatus.OK);
    }
  }

}
