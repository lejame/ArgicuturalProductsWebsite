package nongsansach.service;

import nongsansach.config.cloudinary.CloudinaryService;
import nongsansach.service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class FileService implements FileServiceImp {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Map> save_file(MultipartFile[] listFiles) {
        try {
            return Arrays.stream(listFiles).map((file -> {
                return cloudinaryService.upload(file);
            })).toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }
    }

}
