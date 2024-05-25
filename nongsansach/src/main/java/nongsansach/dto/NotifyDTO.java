package nongsansach.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDTO {
    private int id;
    private String date;
    private String title;
    private String content;
    private String img;
}
