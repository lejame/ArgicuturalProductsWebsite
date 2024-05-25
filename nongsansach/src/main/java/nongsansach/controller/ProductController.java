package nongsansach.controller;

import jakarta.validation.Valid;
import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.ReviewsEntity;
import nongsansach.Entity.SimpleFactory.StateFactory;
import nongsansach.Entity.State.ProductService;
import nongsansach.dto.ProductDTO;
import nongsansach.payload.request.InsertProductRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.ProductRepository;
import nongsansach.service.Imp.ProductServiceImp;
import nongsansach.service.Imp.ReviewsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ReviewsServiceImp reviewsServiceImp;
    @Autowired
    ProductServiceImp productServiceImp;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("")
    public ResponseEntity<?> insertProduct(@RequestPart("file") MultipartFile[] files, @RequestPart("data") InsertProductRequest insertProductRequest) {
        BaseResponse baseResponse = new BaseResponse();
        try{
            ProductEntity product = productServiceImp.insertProduct(files, insertProductRequest);
            ProductDTO productDTO = new ProductDTO();
            productDTO.setImages(product.getImages());
            productDTO.setName(product.getName());
            productDTO.setRate(product.getRate());
            productDTO.setCategory(product.getCategoryEntity().getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setOld_price(product.getOld_price());
            productDTO.setDescription(product.getDescription());

            baseResponse.setMessage("Lưu thành công");
            baseResponse.setData(productDTO);

        }catch (Exception e){

            baseResponse.setMessage("Lỗi:"+ e.getMessage());
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User') ")
    @GetMapping()
    public ResponseEntity<?> getAllProduct() {
        List<ProductEntity> productEntities = productServiceImp.get_all_product();
        BaseResponse baseResponse = new BaseResponse();
        List<ProductDTO> list_productDTO = new ArrayList<>();
        for(ProductEntity products: productEntities){
            List<ReviewsEntity> reviewsEntityList = reviewsServiceImp.getAllReview(products);
            ProductDTO productDTO = new ProductDTO();
            productDTO.setCategory(products.getCategoryEntity().getName());
            productDTO.setName(products.getName());
            productDTO.setPrice(products.getPrice());
            String imagesString = products.getImages();
            List<String> list_images = Arrays.asList(imagesString.split(","));
            String[] urlFile = new String[2];
            int i = 0;
            for (String image : list_images) {
                if (i < 2) {
                    urlFile[i] =
                            image;
                    i++;
                }
            }
            productDTO.setImages(urlFile[0]+","+urlFile[1]);
            productDTO.setRate(products.getRate());
            productDTO.setOld_price(products.getOld_price());
            productDTO.setReview_number(reviewsEntityList.size());
            productDTO.setId(products.getId());
            productDTO.setQuantity(products.getQuantity());
            StateFactory stateFactory  = new StateFactory();
            ProductService productService =stateFactory.createState(products.getQuantity());
            productDTO.setStateProduct(productService.handleRequest());
            productDTO.setDescription(products.getDescription());
            productDTO.setHSD(products.getHSD());
            productDTO.setSize(products.getSize());
            list_productDTO.add(productDTO);
        }
        baseResponse.setData(list_productDTO);
        baseResponse.setMessage("Trả dữ liệu thành công");

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        ProductDTO productDTO = new ProductDTO();
        ProductEntity productEntity = productServiceImp.find_product_id(id);
        productDTO.setName(productEntity.getName());
        productDTO.setRate(productEntity.getRate());
        productDTO.setCategory(productDTO.getCategory());
        productDTO.setPrice(productDTO.getPrice());
        productDTO.setImages(productDTO.getImages());
        productDTO.setDescription(productDTO.getDescription());

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(productDTO);
        baseResponse.setMessage("Dữ liệu được dữ liệu");
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id){
        productServiceImp.delete_by_id(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(true);
        baseResponse.setMessage("Xóa thành công");
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }


}
