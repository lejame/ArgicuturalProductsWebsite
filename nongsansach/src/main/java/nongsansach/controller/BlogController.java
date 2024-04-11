package nongsansach.controller;

import nongsansach.Entity.BlogEntity;
import nongsansach.dto.BlogDTO;
import nongsansach.payload.request.BlogRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {


    @Autowired
    BlogServiceImp blogServiceImp;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("")
    public ResponseEntity<?> addBlog(@RequestPart("file") MultipartFile[] file,@RequestPart("data") BlogRequest blogRequest) {
        BlogEntity blogEntity = blogServiceImp.insertBlog(file,blogRequest);
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blogEntity.getId());
        blogDTO.setName(blogEntity.getName());
        blogDTO.setDate(blogEntity.getDate());
        blogDTO.setContent(blogEntity.getContent());
        blogDTO.setAuthor(blogEntity.getAuthor());
        blogDTO.setImage(blogEntity.getImage());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(blogDTO);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping()
    public ResponseEntity<?> getAllBlog() {
        List<BlogEntity> blogEntity = blogServiceImp.getAllBlog();
        List<BlogDTO> blogDTOS = new ArrayList<>();
        for (BlogEntity blog : blogEntity) {
            BlogDTO blogDTO =new BlogDTO();
            blogDTO.setId(blog.getId());
            blogDTO.setName(blog.getName());
            blogDTO.setDate(blog.getDate());
            blogDTO.setContent(blog.getContent());
            blogDTO.setAuthor(blog.getAuthor());
            blogDTO.setImage(blog.getImage());
            blogDTOS.add(blogDTO);
        }

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(blogDTOS);
        baseResponse.setMessage("Lấy dữ liệu thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
