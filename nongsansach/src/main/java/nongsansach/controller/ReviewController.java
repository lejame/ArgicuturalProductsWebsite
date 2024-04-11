package nongsansach.controller;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.ReviewsEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.dto.ReviewDTO;
import nongsansach.payload.request.ReviewRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.ProductServiceImp;
import nongsansach.service.Imp.ReviewsServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    ReviewsServiceImp reviewsServiceImp;

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PostMapping("")
    public ResponseEntity<?> saveReview(@RequestPart("data") ReviewRequest reviewRequest) {
        ReviewsEntity reviewsEntity = new ReviewsEntity();
        BaseResponse baseResponse = new BaseResponse();
        UsersEntity usersEntity = userServiceImp.find_user_email(reviewRequest.getEmail());
        if(usersEntity != null){
            ProductEntity productEntity = productServiceImp.find_product_id(reviewRequest.getIdProduct());
            reviewsEntity.setRate(reviewRequest.getRate());
            reviewsEntity.setUsersEntity(usersEntity);
            reviewsEntity.setProductEntity(productEntity);
            reviewsEntity.setComment(reviewRequest.getComment());
            reviewsEntity.setTimestamp(reviewRequest.getTime());

            reviewsServiceImp.save_review(reviewsEntity);
            List<ReviewsEntity> reviewsEntityList = reviewsServiceImp.getAllReview(productEntity);
            int rate = 0;
            for (ReviewsEntity reviews : reviewsEntityList) {
                rate += reviews.getRate();
            }
            if (reviewsEntityList.size() == 1) {
                productEntity.setRate(reviewRequest.getRate());
            } else {
                productEntity.setRate(rate / reviewsEntityList.size());

            }
            productServiceImp.save_product(productEntity);

            baseResponse.setMessage("Lưu thành công");
            baseResponse.setData(reviewsEntity);
        }else {
            baseResponse.setMessage("Lỗi");
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('User') or hasAnyAuthority('Admin') ")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllReviewOfProduct(@PathVariable int id) {
        ProductEntity product = productServiceImp.find_product_id(id);
        List<ReviewsEntity> reviewsEntityList = reviewsServiceImp.getAllReview(product);
        List<ReviewDTO> list = new ArrayList<>();
        for (ReviewsEntity reviewsEntity : reviewsEntityList) {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setComment(reviewsEntity.getComment());
            reviewDTO.setUser_name(reviewsEntity.getUsersEntity().getFullname());
            reviewDTO.setRate(reviewsEntity.getRate());
            reviewDTO.setTime(reviewsEntity.getTimestamp());

            list.add(reviewDTO);
        }
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        baseResponse.setMessage("");

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
