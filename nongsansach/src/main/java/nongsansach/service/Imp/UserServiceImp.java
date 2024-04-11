package nongsansach.service.Imp;

import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;

public interface UserServiceImp {
    void saveUserEntity(UsersEntity usersEntity);
    RolesEntity find_role_of_user(String name);

    boolean check_user_oauth2(String email);

    Boolean check_exit_User(String name);
    UsersEntity find_user_email(String email);
}
