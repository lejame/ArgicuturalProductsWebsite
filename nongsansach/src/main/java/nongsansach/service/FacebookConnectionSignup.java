package nongsansach.service;

import nongsansach.Entity.UsersEntity;
import nongsansach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    UserRepository userRepository;
    @Override
    public String execute(Connection<?> connection) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(connection.getDisplayName());
        userRepository.save(usersEntity);
        return usersEntity.getEmail();
    }
}
