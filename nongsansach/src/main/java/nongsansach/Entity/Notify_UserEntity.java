package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Notify_UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "notifyUserEntity")
    private List<UsersEntity> usersEntities = new ArrayList<>();


    @OneToMany(mappedBy = "notifyUserEntity")
    private List<NotifyEntity> notifyEntities = new ArrayList<>();
}
