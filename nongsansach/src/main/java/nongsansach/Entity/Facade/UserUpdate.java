package nongsansach.Entity.Facade;

import lombok.AllArgsConstructor;
import nongsansach.Entity.Facade.Data.Update;
import nongsansach.Entity.UsersEntity;
import nongsansach.payload.request.UserRequest;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.security.crypto.password.PasswordEncoder;
@AllArgsConstructor
public class UserUpdate {
    public void update(UserServiceImp userServiceImp, Update update, PasswordEncoder passwordEncoder){
        UsersEntity usersEntity = userServiceImp.find_user_email(update.getEmail());
        usersEntity.setAddress(update.getAddress());
        usersEntity.setPhone(update.getPhone());
        usersEntity.setPassword(passwordEncoder.encode(update.getPassword()));
        userServiceImp.saveUserEntity(usersEntity);
    }
}
