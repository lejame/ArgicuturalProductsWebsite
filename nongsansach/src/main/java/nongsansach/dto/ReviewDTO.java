package nongsansach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ReviewDTO {
    private  String user_name;

    private String time;

    private int rate;
    private String comment;

}
