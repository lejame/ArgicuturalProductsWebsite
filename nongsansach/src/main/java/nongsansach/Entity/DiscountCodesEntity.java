package nongsansach.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class DiscountCodesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int low_price;

    private boolean status;

    private String expiration_date;

    public DiscountCodesEntity(){

    }
    public DiscountCodesEntity(String name,int low_price,String expiration_date){
        this.name = name;
        this.low_price = low_price;
        this.expiration_date = expiration_date;
        this.status = false;
    }
}
