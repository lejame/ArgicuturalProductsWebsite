package nongsansach.payload.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InsertProductRequest {

    private String productName;

    private double price;

    private double old_price;

    @Column(length = 1000)
    private String description;

    private String name_category;

    private int rate;

    private int quantity;

    private String size;

    private String hsd;

    private String provider;

}
