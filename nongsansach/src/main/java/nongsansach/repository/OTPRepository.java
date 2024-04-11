package nongsansach.repository;

import nongsansach.Entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity,Integer> {
    OTPEntity findByName(int opt);

}
