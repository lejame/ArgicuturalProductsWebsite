package nongsansach.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
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

    @ManyToOne
    @JoinColumn(name = "id_roles")
    private RolesEntity role;

    @OneToMany(mappedBy = "usersEntity")
    private List<ReviewsEntity> reviewsEntityList;



    public List<ReviewsEntity> getReviewsEntityList() {
        return reviewsEntityList;
    }

    public void setReviewsEntityList(List<ReviewsEntity> reviewsEntityList) {
        this.reviewsEntityList = reviewsEntityList;
    }

    public UsersEntity(){

    }
    public UsersEntity(String email, String password, String fullname, String phone_number, String major,String mssv, RolesEntity role) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public RolesEntity getRole() {
        return role;
    }

    public void setRole(RolesEntity role) {
        this.role = role;
    }
}
