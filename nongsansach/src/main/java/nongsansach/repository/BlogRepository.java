package nongsansach.repository;

import nongsansach.Entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogEntity,Integer> {

}
