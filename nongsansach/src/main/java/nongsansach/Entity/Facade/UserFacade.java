package nongsansach.Entity.Facade;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nongsansach.Entity.Facade.Data.Register;
import nongsansach.Entity.Facade.Data.Update;
import nongsansach.payload.request.LoginRequest;
import nongsansach.payload.request.UserRequest;
import nongsansach.service.Imp.RoleServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import nongsansach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor

public class UserFacade {
    public UserUpdate userUpdate = new UserUpdate();
    public UserRegister userRegister = new UserRegister();

    public void UserFacadeActionUpdate(UserServiceImp userServiceImp, PasswordEncoder passwordEncoder, Update update){
        userUpdate.update(userServiceImp,update,passwordEncoder);
    }
    public void UserFacadeActionRegister(UserServiceImp userServiceImp, PasswordEncoder passwordEncoder, RoleServiceImp roleServiceImp, Register register){
        userRegister.register(userServiceImp,register,roleServiceImp,passwordEncoder);
    }
}
