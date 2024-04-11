package nongsansach.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
    private String subject;
    private String message;

    private String content_subject = "ĐÂY LÀ MẬT MÃ XÁC NHẬN";
    public MailRequest (String subject,String message){
        this.subject = subject;
        this.message = message;
    }
    public MailRequest(){
        subject = content_subject;
        message = "Mã opt của bạn là:";
    }

}
