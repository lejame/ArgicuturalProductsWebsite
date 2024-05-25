package nongsansach.controller;

import jakarta.validation.Valid;
import nongsansach.Entity.Builder.UserEntiryBuilder;
import nongsansach.Entity.Facade.Data.Register;
import nongsansach.Entity.Facade.UserFacade;
import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.payload.request.LoginRequest;
import nongsansach.service.Imp.RoleServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImp roleServiceImp;

    @Autowired
    UserServiceImp userServiceImp;


    @PostMapping("")
    public ResponseEntity<?> register(@Valid LoginRequest loginRequest) {
        if (userServiceImp.check_exit_User(loginRequest.getUsername()) == false) {
            UserFacade userFacade = new UserFacade();
            Register register = new Register(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getFirst_name(), loginRequest.getLast_name());
            userFacade.UserFacadeActionRegister(userServiceImp,passwordEncoder,roleServiceImp,register);
            return new ResponseEntity<>("Lưu Thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("tài khoản đã tồn tại", HttpStatus.OK);
    }
}
