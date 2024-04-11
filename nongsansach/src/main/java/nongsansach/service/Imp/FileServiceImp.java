package nongsansach.service.Imp;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileServiceImp {
    List<Map> save_file(MultipartFile[] file);
}
