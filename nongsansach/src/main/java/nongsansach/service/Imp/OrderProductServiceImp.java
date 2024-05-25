package nongsansach.service.Imp;

import nongsansach.Entity.OrderProductEntity;

import java.util.List;

public interface OrderProductServiceImp {
    void save (OrderProductEntity orderProductEntity);

    List<OrderProductEntity> get_all();

}
