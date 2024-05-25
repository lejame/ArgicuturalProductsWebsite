package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class TransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "order_id")
    @OneToOne
    private OrderEntity order;

    private String payMethod;

    private Date payDate;

    private String firstName;

    private String lastName;

    private String address;

    private String town;

    private String email;

    private String phone;

    private boolean status;

    public TransactionHistoryEntity() {

    }


    public TransactionHistoryEntity(OrderEntity orderEntity, Date date,String firstName,String lastName,String address,String town,String email,String phone) {
        this.payDate = date;
        this.order = orderEntity;
        this.payMethod = "paypal";
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.town = town;
        this.email = email;
        this.phone = phone;
        this.status  = false;
    }
}
