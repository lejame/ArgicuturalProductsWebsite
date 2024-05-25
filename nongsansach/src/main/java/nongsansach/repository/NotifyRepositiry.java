package nongsansach.repository;

import nongsansach.Entity.NotifyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepositiry extends JpaRepository<NotifyEntity,Integer> {
}
