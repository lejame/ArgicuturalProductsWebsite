package nongsansach.service.Imp;

import nongsansach.Entity.OrderEntity;
import nongsansach.Entity.TransactionHistoryEntity;
import nongsansach.Entity.UsersEntity;

import java.util.List;

public interface TransactionHistoryServiceImp {
    TransactionHistoryEntity save_entity(TransactionHistoryEntity transactionHistoryEntity);
    List<TransactionHistoryEntity> get_all();

    List<TransactionHistoryEntity> get_all_By_User(UsersEntity usersEntity);

    TransactionHistoryEntity get_by_id(int id);

    void delete_by_id(int id);

    TransactionHistoryEntity get_by_orderId(OrderEntity orderEntity);



}
