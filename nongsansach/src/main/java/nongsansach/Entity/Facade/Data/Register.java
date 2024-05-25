package nongsansach.Entity.Facade.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Register {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
}
