package nongsansach.repository;

import nongsansach.Entity.Notify_UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyUserRepository extends JpaRepository<Notify_UserEntity,Integer> {
    Notify_UserEntity save(Notify_UserEntity notifyUserEntity);

    Notify_UserEntity findById(int id);
}
