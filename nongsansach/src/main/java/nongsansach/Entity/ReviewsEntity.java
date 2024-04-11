package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user")
    @ManyToOne
    private UsersEntity usersEntity;

    @JoinColumn(name = "product")
    @ManyToOne
    private ProductEntity productEntity;

    private int rate;

    private String comment;

    private String timestamp;

}
