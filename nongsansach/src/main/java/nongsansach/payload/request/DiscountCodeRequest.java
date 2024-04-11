package nongsansach.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DiscountCodeRequest {
    private int id;

    private String name;

    private int low_price;

    private String expiration_date;
}
