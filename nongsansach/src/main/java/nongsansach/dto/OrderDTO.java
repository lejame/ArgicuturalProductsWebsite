package nongsansach.dto;

import lombok.Getter;
import lombok.Setter;
import nongsansach.Entity.ProductEntity;
import nongsansach.Entity.UsersEntity;

import java.util.List;

@Setter
@Getter
public class OrderDTO {
    private int id;
    private UsersEntity usersEntity;
    private List<ProductEntity> productEntity;
    private double price;

}
