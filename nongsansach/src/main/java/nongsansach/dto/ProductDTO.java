package nongsansach.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int id;

    private String name;

    private Double price;

    private Double old_price;

    private String category;

    private String images;

    public int rate;

    private int review_number;

    private int quantity;

    private String stateProduct;

    private int quantity_stock;

    private String size;

    private String HSD;

    private String description;

}
