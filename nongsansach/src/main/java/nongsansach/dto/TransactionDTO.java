package nongsansach.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nongsansach.Entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private int orderId;

    private String payMethod;

    private Date payDate;

    private String firstName;

    private String lastName;

    private String address;

    private String town;

    private String email;

    private String phone;

    private boolean status;
}
