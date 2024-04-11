package nongsansach.service.Imp;

import nongsansach.Entity.BlogEntity;
import nongsansach.payload.request.BlogRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogServiceImp {
    void save_blog(BlogEntity blogEntity);
    BlogEntity insertBlog(MultipartFile[] file, BlogRequest blogRequest);

    List<BlogEntity> getAllBlog();
}
