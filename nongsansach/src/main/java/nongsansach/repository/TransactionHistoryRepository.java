package nongsansach.repository;

import nongsansach.Entity.OrderEntity;
import nongsansach.Entity.TransactionHistoryEntity;
import nongsansach.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity,Integer> {

    List<TransactionHistoryEntity> findTransactionHistoryEntitiesByOrder_UsersOrderEntity(UsersEntity usersEntity);
    TransactionHistoryEntity findById(int id);

    TransactionHistoryEntity findByOrder(OrderEntity orderEntity);
}
