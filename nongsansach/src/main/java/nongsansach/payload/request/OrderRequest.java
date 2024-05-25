package nongsansach.payload.request;

import lombok.Getter;
import lombok.Setter;
import nongsansach.Entity.Data.ProductData;
import nongsansach.Entity.ProductEntity;

import java.util.List;
@Setter
@Getter
public class OrderRequest {

    private List<ProductData> product;

    private String email;

    private String emailAddress;

    private double price;

    private String description;

    private String firstName;

    private String lastName;

    private String address;

    private String town;

    private String phone;

}
