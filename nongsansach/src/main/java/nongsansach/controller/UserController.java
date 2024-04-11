package nongsansach.controller;

import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping()
    public ResponseEntity<?> getProfileUser(Authentication authentication){
        BaseResponse baseResponse = new BaseResponse();

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
