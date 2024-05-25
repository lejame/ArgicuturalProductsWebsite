package nongsansach.controller;

import nongsansach.Entity.NotifyEntity;
import nongsansach.Entity.Notify_UserEntity;
import nongsansach.Entity.Observer.Notifiers;
import nongsansach.Entity.Observer.Subject;
import nongsansach.dto.NotifyDTO;
import nongsansach.payload.request.NotifyRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.repository.NotifyRepositiry;
import nongsansach.repository.NotifyUserRepository;
import nongsansach.service.Imp.FileServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("notify")
@CrossOrigin("*")
public class NotifyController {

    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    NotifyRepositiry notifyRepositiry;

    @Autowired
    NotifyUserRepository notifyUserRepository;

    @PostMapping("/admin")
    public ResponseEntity<?> sendNotice(@RequestPart("file") MultipartFile[] file, @RequestPart("data") NotifyRequest notifyRequest) {
        BaseResponse baseResponse = new BaseResponse();
        if (notifyUserRepository.findById(1) == null) {
            Notify_UserEntity notifyUserEntity = new Notify_UserEntity();
            notifyUserEntity.setId(1);
            notifyUserRepository.save(notifyUserEntity);
        }
            try {
                Subject subject = new Subject();
                List<Map> result = fileServiceImp.save_file(file);

                Notify_UserEntity notifyUserEntity = notifyUserRepository.findById(1);

                List<String> list_file = new ArrayList<>();
                result.forEach((uploadedResult) -> {
                    list_file.add(uploadedResult.get("secure_url").toString());
                });

                Notifiers notifiers1 = new Notifiers();
                subject.add(notifiers1);

                NotifyEntity notify = new NotifyEntity(notifyRequest.getTitle(), notifyRequest.getContent(), list_file.get(0));
                notify.setNotifyUserEntity(notifyUserEntity);
                Date currentDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDateTime = formatter.format(currentDate);
                notify.setDate(formattedDateTime);
                subject.notifyForUser(notify, userServiceImp, notifyUserEntity, notifyUserRepository, notifyRepositiry);
                baseResponse.setMessage("gửi thành công");

            } catch (Exception exception) {
                System.out.print(exception.getMessage());
            }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @GetMapping("")
    public ResponseEntity<?> get_all_notify() {
        BaseResponse baseResponse = new BaseResponse();
        List<NotifyDTO> notifyDTOS = new ArrayList<>();
        List<NotifyEntity> list = notifyRepositiry.findAll();
        for (NotifyEntity dto : list) {
            NotifyDTO notifyDTO = new NotifyDTO();
            notifyDTO.setId(dto.getId());
            notifyDTO.setImg(dto.getImage());
            notifyDTO.setContent(dto.getContent());
            notifyDTO.setDate(dto.getDate());
            notifyDTO.setTitle(dto.getTitle());
            notifyDTOS.add(notifyDTO);
        }

        baseResponse.setData(notifyDTOS);
        baseResponse.setMessage("Lấy dữ liệu thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
