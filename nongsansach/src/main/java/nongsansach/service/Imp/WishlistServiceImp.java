package nongsansach.service.Imp;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.Entity.WishlistEntity;

import java.util.List;

public interface WishlistServiceImp {

    public WishlistEntity addProductToWishList(UsersEntity usersEntity, ProductEntity productEntity);

//    public List<ProductEntity> getAll(UsersEntity usersEntity);

}
