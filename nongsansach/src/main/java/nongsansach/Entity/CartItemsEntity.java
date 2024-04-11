package nongsansach.Entity;

import jakarta.persistence.*;

import java.util.List;

public class CartItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name = "cart")
    private CartEntity cartEntity;

    @OneToMany
    @JoinColumn(name = "products")
    public List<ProductEntity> productEntities;

    private int quantity;
}
