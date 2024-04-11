package nongsansach.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ReviewRequest {

    private String comment;

    private int idProduct;

    private String email;

    private int rate;
    private String time;


}
