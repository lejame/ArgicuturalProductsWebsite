package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(length = 1000)
    private String description;

    private Double price;

    private Double old_price;

    private int quantity;

    private int rate;

    private String size;

    private String provider;

    private String NSX;

    private String HSD;



    @ManyToOne
    @JoinColumn(name = "categoryEntity")
    private CategoryEntity categoryEntity;

    private String images;

    @OneToMany(mappedBy="productEntity")
    private List<ReviewsEntity> reviewsEntity;



}
