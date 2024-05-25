package nongsansach.dto;

import lombok.Getter;
import lombok.Setter;
import nongsansach.Entity.ProductEntity;

@Setter
@Getter
public class ProductOrderDTO {
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

    private String description;

    private int quantity_stock;

    private String date;

    private boolean status;
}
