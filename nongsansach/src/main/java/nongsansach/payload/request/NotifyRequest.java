package nongsansach.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class NotifyRequest {
    private String title;
    private String content;
    private String date;

}
