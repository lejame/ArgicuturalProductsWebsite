package nongsansach.Entity.Builder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import nongsansach.Entity.ReviewsEntity;
import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;

import java.util.List;

public class UserEntiryBuilder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String fullname;
    private RolesEntity role;
    private String address;
    private String phone;
    private List<ReviewsEntity> reviewsEntityList;

    public UserEntiryBuilder() {
    }

    public UserEntiryBuilder buildEmail(String email){
        this.email = email;
        return this;
    }
    public UserEntiryBuilder buildPassword(String password){
        this.password = password;
        return this;
    }
    public UserEntiryBuilder buildFullName(String fullname){
        this.fullname = fullname;
        return this;
    }
    public UserEntiryBuilder buildRole(RolesEntity role){
        this.role = role;
        return this;
    }
    public UserEntiryBuilder buildAddress(String address){
        this.address = address;
        return this;
    }public UserEntiryBuilder buildPhone(String phone){
        this.phone = phone;
        return this;
    }

    public UsersEntity build(){
        return new UsersEntity(email, password, fullname, role, address, phone);
    }
}
