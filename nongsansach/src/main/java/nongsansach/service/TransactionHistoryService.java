package nongsansach.service;

import nongsansach.Entity.OrderEntity;
import nongsansach.Entity.TransactionHistoryEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.repository.TransactionHistoryRepository;
import nongsansach.service.Imp.TransactionHistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService implements TransactionHistoryServiceImp {

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;


    @Override
    public TransactionHistoryEntity save_entity(TransactionHistoryEntity transactionHistoryEntity) {
        return transactionHistoryRepository.save(transactionHistoryEntity);
    }

    @Override
    public List<TransactionHistoryEntity> get_all() {
        return transactionHistoryRepository.findAll();
    }

    @Override
    public List<TransactionHistoryEntity> get_all_By_User(UsersEntity usersEntity) {
        return transactionHistoryRepository.findTransactionHistoryEntitiesByOrder_UsersOrderEntity(usersEntity);
    }

    @Override
    public TransactionHistoryEntity get_by_id(int id) {
        return transactionHistoryRepository.findById(id);
    }

    @Override
    public void delete_by_id(int id) {
        transactionHistoryRepository.deleteById(id);
    }

    @Override
    public TransactionHistoryEntity get_by_orderId(OrderEntity orderEntity) {
        return transactionHistoryRepository.findByOrder(orderEntity);
    }


}
