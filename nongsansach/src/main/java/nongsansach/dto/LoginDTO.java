package nongsansach.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {
    private String email;

    private String token;

    private String username;

    private String role;

}
