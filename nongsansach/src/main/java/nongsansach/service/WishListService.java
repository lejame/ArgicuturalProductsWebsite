package nongsansach.service;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.Entity.WishlistEntity;
import nongsansach.repository.WishListRepository;

import nongsansach.service.Imp.UserServiceImp;
import nongsansach.service.Imp.WishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService implements WishlistServiceImp {

    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    UserServiceImp userServiceImp;

    @Override
    public WishlistEntity addProductToWishList(UsersEntity usersEntity, ProductEntity productEntity) {
        if (usersEntity.getWishlistEntity() == null) {
            WishlistEntity wishlistEntityReturn = new WishlistEntity();
            List<ProductEntity> productWishNew = new ArrayList<>();
            productWishNew.add(productEntity);
            WishlistEntity wishNew = new WishlistEntity();
            wishNew.setProductEntities(productWishNew);
            wishNew.setUsersEntity(usersEntity);
            wishlistEntityReturn = wishListRepository.save(wishNew);
            return wishlistEntityReturn;
        } else {
            WishlistEntity wishlistEntity = wishListRepository.findById(usersEntity.getWishlistEntity().getId());
            List<ProductEntity> productEntities = wishlistEntity.getProductEntities();
            if (productEntities.contains(productEntity)) {
                return wishlistEntity;
            }
            productEntities.add(productEntity);
            wishlistEntity.setProductEntities(productEntities);
            WishlistEntity wishList = wishListRepository.save(wishlistEntity);
            return wishList;
        }
    }

//    @Override
//    public List<ProductEntity> getAll(UsersEntity usersEntity) {
//        List<?> productEntities;
//        productEntities = wishListRepository.findWishlistEntitiesById(usersEntity.getWishlistEntity().getId());
//        return (List<ProductEntity>) productEntities;
//    }

}
