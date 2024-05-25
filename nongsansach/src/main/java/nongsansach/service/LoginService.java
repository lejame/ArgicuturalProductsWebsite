package nongsansach.service;

import com.google.gson.Gson;
import nongsansach.Entity.UsersEntity;
import nongsansach.payload.response.RoleResponse;
import nongsansach.repository.UserRepository;
import nongsansach.service.Imp.LoginServiceImp;
import nongsansach.utils.JwtUltils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {


    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUltils jwtUltils;

    private Gson gson = new Gson();

    @Override
    public String checkLogin(String username, String password) {
        String token = "";
        UsersEntity usersEntity = usersRepository.findByEmail(username);
        if (usersEntity != null){
            if(passwordEncoder.matches(password, usersEntity.getPassword())){
                //Tạo token từ key đã sinh ra và lưu trữ trước đó
                RoleResponse roleResponse = new RoleResponse();
                roleResponse.setName(usersEntity.getRole().getName());
                String roles = gson.toJson(roleResponse);
                token = jwtUltils.createToken(roles);
            }
        }

        return token;
    }

    @Override
    public String checkLoginByGmail(String email) {
        String token = "";
        UsersEntity usersEntity = usersRepository.findByEmail(email);
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setName(usersEntity.getRole().getName());
        String roles = gson.toJson(roleResponse);
        token = jwtUltils.createToken(roles);
        return token;
    }
}
