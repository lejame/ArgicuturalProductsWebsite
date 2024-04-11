package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user")
    @ManyToOne
    private UsersEntity usersEntity;

    @JoinColumn(name = "discountId")
    @ManyToOne
    private DiscountCodesEntity discountCodesEntity;

    private float total;

    private float discount_amount;

//    @JoinColumn(name = "cart")
//    private CartItemsEntity

}
