package nongsansach.controller;

import jakarta.validation.Valid;
import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.dto.LoginDTO;
import nongsansach.payload.request.LoginRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.RoleRepository;
import nongsansach.service.Imp.LoginServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sign-in")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private LoginServiceImp loginServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<?> login(@Valid LoginRequest loginRequest){

        String token = loginServiceImp.checkLogin(loginRequest.getUsername(),loginRequest.getPassword());
        BaseResponse baseResponse = new BaseResponse();

        if(token.equals("")){
            baseResponse.setStatusCode(200);
            baseResponse.setMessage("Fail");
        }
        else {
            RolesEntity rolesEntity = userServiceImp.find_role_of_user(loginRequest.getUsername());
            UsersEntity usersEntity = userServiceImp.find_user_email(loginRequest.getUsername());
            LoginDTO loginDTO  =new LoginDTO();
            loginDTO.setEmail(loginRequest.getUsername());
            loginDTO.setRole(rolesEntity.getName());
            loginDTO.setToken(token);
            loginDTO.setUsername(usersEntity.getFullname());
            baseResponse.setStatusCode(200);
            baseResponse.setData(loginDTO);
            baseResponse.setMessage("Success");
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
