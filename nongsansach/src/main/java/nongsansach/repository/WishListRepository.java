package nongsansach.repository;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.Entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishlistEntity,Integer> {
    WishlistEntity findById(int id);

    List<?> findWishlistEntitiesById(int id);

}
