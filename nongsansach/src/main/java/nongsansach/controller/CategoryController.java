package nongsansach.controller;

import jakarta.validation.Valid;
import nongsansach.Entity.CategoryEntity;
import nongsansach.dto.CategoryDTO;
import nongsansach.payload.request.CategoryRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/{name}")
    public ResponseEntity<?> add(@PathVariable String name) {
        BaseResponse baseResponse = new BaseResponse();
        if (categoryServiceImp.check_category(name)) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            categoryServiceImp.save_category(categoryEntity);
            baseResponse.setData(name);
            baseResponse.setMessage("Lưu thành công");
        } else {
            baseResponse.setMessage("Lưu thất bại");
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('User') or hasAnyAuthority('Admin')")
    @GetMapping("")
    public ResponseEntity<?> getALlCategory() {
        List<CategoryDTO> productDTOS = categoryServiceImp.getAllCategory();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productDTOS);
        baseResponse.setMessage("Lấy dữ liệu thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
