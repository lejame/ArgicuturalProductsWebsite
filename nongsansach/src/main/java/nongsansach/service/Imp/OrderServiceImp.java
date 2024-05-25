package nongsansach.service.Imp;

import nongsansach.Entity.OrderEntity;
import nongsansach.dto.OrderDTO;

public interface OrderServiceImp {
    public OrderDTO save_order(OrderEntity orderEntity);
    public OrderEntity get_order_by_Id(int id);
}
