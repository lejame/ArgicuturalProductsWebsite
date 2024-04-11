package nongsansach.service;

import nongsansach.Entity.BlogEntity;
import nongsansach.payload.request.BlogRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.BlogRepository;
import nongsansach.service.Imp.BlogServiceImp;
import nongsansach.service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class BLogService implements BlogServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    BlogRepository blogRepository;

    @Override
    public void save_blog(BlogEntity blogEntity) {
        blogRepository.save(blogEntity);
    }

    @Override
    public BlogEntity insertBlog(MultipartFile[] file, BlogRequest blogRequest) {
        BlogEntity blogEntity = new BlogEntity();
        try {

            List<Map> files = fileServiceImp.save_file(file);

            blogEntity.setName(blogRequest.getName());
            blogEntity.setAuthor(blogRequest.getAuthor());
            blogEntity.setImage(files.get(0).get("secure_url").toString());
            blogEntity.setContent(blogRequest.getContent());
            blogEntity.setDate(blogRequest.getDate());
            save_blog(blogEntity);

        } catch (Exception e) {
            System.out.print("check issue:" + e.getMessage());
        }
        return blogEntity;
    }

    @Override
    public List<BlogEntity> getAllBlog() {
        return blogRepository.findAll();
    }
}
