package nongsansach.service;

import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.repository.UserRepository;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository usersRepository;
    @Override
    public void saveUserEntity(UsersEntity usersEntity) {
        usersRepository.save(usersEntity);
    }

    @Override
    public RolesEntity find_role_of_user(String name) {
        UsersEntity usersEntity = usersRepository.findByEmail(name);
        return usersEntity.getRole();
    }

    @Override
    public boolean check_user_oauth2(String email) {
        return usersRepository.findByEmail(email)!=null ? true:false;
    }

    @Override
    public Boolean check_exit_User(String name) {
        UsersEntity usersEntity = usersRepository.findByEmail(name);
        if(usersEntity !=null){
            return true;
        }
        return false;
    }

    @Override
    public UsersEntity find_user_email(String email) {
        return usersRepository.findByEmail(email);
    }


}
