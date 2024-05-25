package nongsansach.Entity.Facade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nongsansach.Entity.Builder.UserEntiryBuilder;
import nongsansach.Entity.Facade.Data.Register;
import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.payload.request.LoginRequest;
import nongsansach.service.Imp.RoleServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.security.crypto.password.PasswordEncoder;
@Getter
@Setter
@AllArgsConstructor
public class UserRegister {
    public void register(UserServiceImp userServiceImp, Register register, RoleServiceImp roleServiceImp, PasswordEncoder passwordEncoder){
        boolean checkUser = userServiceImp.check_exit_User(register.getUsername());
        if (!checkUser) {
            RolesEntity rolesEntity = roleServiceImp.find_by_name("User");
            UsersEntity userEntityBuilder = new UserEntiryBuilder().buildEmail(register.getUsername()).buildPassword(passwordEncoder.encode(register.getPassword()))
                    .buildFullName(register.getFirst_name() + " " + register.getLast_name()).buildRole(rolesEntity).build();
            userServiceImp.saveUserEntity(userEntityBuilder);
        }
    }
}
