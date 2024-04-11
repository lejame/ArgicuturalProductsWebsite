package nongsansach.repository;

import nongsansach.Entity.DiscountCodesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCodesEntity,Integer> {
    DiscountCodesEntity findById(int id);

    DiscountCodesEntity findByName(String name);
}
