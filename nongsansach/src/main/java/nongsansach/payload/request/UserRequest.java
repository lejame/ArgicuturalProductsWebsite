package nongsansach.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String name;

    private String email;

    private String password;

    private String phone;

    private String address;

}
