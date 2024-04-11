package nongsansach.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class InsertProductRequest {

    private String productName;
    private double price;
    private double old_price;
    private String description;

    private String name_category;

    private int rate;

    private int quantity;

}
