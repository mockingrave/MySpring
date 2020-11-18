package myspring.mvc.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.directory}")
    private String FOLDER;


    public void saveFile(MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    public Resource getFile(String fileName){
        Path path = Paths.get(FOLDER + fileName);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
