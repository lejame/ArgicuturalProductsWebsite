package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "user")
@Setter
@Getter
@AllArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullname;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne
    @JoinColumn(name = "role")
    private RolesEntity role;

    @OneToMany(mappedBy = "usersEntity")
    private List<ReviewsEntity> reviewsEntityList;

    @OneToMany(mappedBy = "usersOrderEntity")
    private List<OrderEntity> usersOrderEntity;

    @ManyToOne
    @JoinColumn(name = "notify_user")
    private Notify_UserEntity notifyUserEntity;

    @OneToOne
    @JoinColumn(name = "wishlist_entity")
    private WishlistEntity wishlistEntity;

    public UsersEntity() {

    }

    public UsersEntity(String email, String password, String fullname, RolesEntity role) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public UsersEntity(String email, String password, String fullname, RolesEntity role, String address, String phone) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.phone = phone;
        this.address = address;
    }

}
