package nongsansach.Entity.Facade.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Update {
    private String email;

    private String phone;

    private String username;

    private String password;

    private String address;
}
