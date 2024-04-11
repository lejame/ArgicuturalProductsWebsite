package nongsansach.repository;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity,Integer> {
    List<ReviewsEntity> findByProductEntity(ProductEntity productEntity);


}