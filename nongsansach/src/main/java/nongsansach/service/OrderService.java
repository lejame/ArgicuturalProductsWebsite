package nongsansach.service;

import nongsansach.Entity.OrderEntity;
import nongsansach.dto.OrderDTO;
import nongsansach.repository.OrderRepository;
import nongsansach.service.Imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO save_order(OrderEntity orderEntity) {
        OrderEntity order = orderRepository.save(orderEntity);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setUsersEntity(order.getUsersOrderEntity());
        return orderDTO;
    }

    @Override
    public OrderEntity get_order_by_Id(int id) {
        return orderRepository.findById(id);
    }

}
