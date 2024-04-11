package nongsansach.service.Imp;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.ReviewsEntity;

import java.util.List;

public interface ReviewsServiceImp {
    void save_review(ReviewsEntity reviewsEntity);

    List<ReviewsEntity> getAllReview(ProductEntity productEntity);
}
