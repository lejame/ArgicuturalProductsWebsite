package nongsansach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class AccountOrderDTO {
    private int orderId;
    private String date;
    private Boolean status;
    private String total;

}
