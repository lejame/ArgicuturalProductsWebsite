package nongsansach.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryRequest {
    @NotBlank (message = "Không được để trống")
    private String name_category;

}
