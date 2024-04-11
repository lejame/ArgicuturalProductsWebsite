package nongsansach.service;

import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.ReviewsEntity;
import nongsansach.repository.ReviewsRepository;
import nongsansach.service.Imp.ReviewsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService implements ReviewsServiceImp {
    @Autowired
    ReviewsRepository reviewsRepository;

    @Override
    public void save_review(ReviewsEntity reviewsEntity) {
        reviewsRepository.save(reviewsEntity);
    }

    @Override
    public List<ReviewsEntity> getAllReview(ProductEntity productEntity) {
        return reviewsRepository.findByProductEntity(productEntity);
    }
}
