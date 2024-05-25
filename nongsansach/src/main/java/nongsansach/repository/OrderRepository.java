package nongsansach.repository;

import nongsansach.Entity.OrderEntity;
import nongsansach.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    OrderEntity findById(int id);

    List<OrderEntity> findByUsersOrderEntity(UsersEntity usersEntity);
}
