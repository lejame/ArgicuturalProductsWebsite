package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user")
    @ManyToOne
    private UsersEntity usersOrderEntity;

    private double price;

    @OneToMany(mappedBy = "order")
    private List<OrderProductEntity> orderProductEntities;

    @OneToOne(mappedBy = "order")
    private TransactionHistoryEntity transactionHistoryEntity;

    public OrderEntity(){

    }
    public OrderEntity(double price, UsersEntity usersEntity){
        this.price = price;
        this.usersOrderEntity = usersEntity;
    }

}
