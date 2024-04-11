package nongsansach.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class BlogRequest {

    private String name;

    private String content;

    private String Date;

    private String author;
}
