package nongsansach.service;

import nongsansach.Entity.OrderProductEntity;
import nongsansach.repository.OrderProductRepository;
import nongsansach.service.Imp.OrderProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService implements OrderProductServiceImp {

    @Autowired
    OrderProductRepository orderProductRepository;
    @Override
    public void save(OrderProductEntity orderProductEntity) {
        orderProductRepository.save(orderProductEntity);
    }

    @Override
    public List<OrderProductEntity> get_all() {
        return orderProductRepository.findAll();
    }
}
