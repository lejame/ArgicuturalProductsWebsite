package nongsansach.controller;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.Entity.WishlistEntity;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.ProductServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import nongsansach.service.Imp.WishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("*")
public class WishListController {

    @Autowired
    WishlistServiceImp wishlistServiceImp;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    ProductServiceImp productServiceImp;

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @PostMapping("/{email}/{id_product}")
    public ResponseEntity<?> addProductWishList(@PathVariable("email") String email, @PathVariable("id_product") int id_product) {
        ProductEntity product = productServiceImp.find_product_id(id_product);
        UsersEntity usersEntity = userServiceImp.find_user_email(email);

        WishlistEntity wishlistEntity = wishlistServiceImp.addProductToWishList(usersEntity, product);
        usersEntity.setWishlistEntity(wishlistEntity);
        userServiceImp.saveUserEntity(usersEntity);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("add thành công");
        baseResponse.setData(wishlistEntity);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
//    @GetMapping("/{email}")
//    public ResponseEntity<?> getProductWishList(@PathVariable("email") String email) {
//        UsersEntity usersEntity = userServiceImp.find_user_email(email);
//        List<?> list = wishlistServiceImp.getAll(usersEntity);
//        BaseResponse baseResponse = new BaseResponse();
//        baseResponse.setMessage("Lấy dữ liệu thành công");
//        baseResponse.setData(list);
//        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
//    }
}
