package nongsansach.controller;

import nongsansach.Entity.Model.MailStructure;
import nongsansach.Entity.OTPEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.payload.request.MailRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.OTPRepository;
import nongsansach.service.Imp.MailServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class MailController {

    @Autowired
    OTPRepository otpRepository;
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    MailServiceImp mailServiceImp;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/send/{mail}")
    public ResponseEntity<?> sendOTP(@PathVariable String mail, MailRequest mailRequest) {
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject(mailRequest.getSubject());
        BaseResponse baseResponse = new BaseResponse();
        UsersEntity user = userServiceImp.find_user_email(mail);
        if (user == null) {
            baseResponse.setMessage("fail");
        } else {
            OTPEntity otpEntity = new OTPEntity(user);
            otpRepository.save(otpEntity);
            mailStructure.setMessage(mailRequest.getMessage() + otpEntity.getName() + "\nTuyệt đối không chia sẽ mã này cho bất kì ai");
            mailServiceImp.sendMail(mail, mailStructure);
            baseResponse.setMessage("success");
            baseResponse.setData(mail);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/confirm/{otp}")
    public ResponseEntity<?> confirm_OTP(@PathVariable int otp) {
        OTPEntity otpEntity = otpRepository.findByName(otp);
        BaseResponse baseResponse = new BaseResponse();
        if (otpEntity == null) {
            baseResponse.setMessage("fail");
        } else {
            otpEntity.setValue(true);
            otpRepository.save(otpEntity);

            baseResponse.setData(true);
            baseResponse.setMessage("Success");
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/{email}/{password}")
    public ResponseEntity<?> changes_password(@PathVariable String email, @PathVariable String password) {
        UsersEntity usersEntity = userServiceImp.find_user_email(email);
        usersEntity.setPassword(passwordEncoder.encode(password));
        userServiceImp.saveUserEntity(usersEntity);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(usersEntity.getEmail());
        baseResponse.setMessage("Success");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
