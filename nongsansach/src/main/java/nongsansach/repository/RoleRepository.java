package nongsansach.repository;

import nongsansach.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity,Integer> {
    RolesEntity findByName(String name);
}
