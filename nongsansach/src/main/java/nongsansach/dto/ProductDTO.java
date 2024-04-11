package nongsansach.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    private int id;

    private String name;

    private String description;

    private Double price;

    private Double old_price;

    private String category;

    private String images;

    public int rate;

    private int review_number;

    private int quantity;

}
