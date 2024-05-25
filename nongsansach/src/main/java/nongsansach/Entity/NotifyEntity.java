package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class NotifyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String image;
    private String date;

    public NotifyEntity(){}
    public NotifyEntity(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    @ManyToOne
    @JoinColumn(name = "notify_user")
    private Notify_UserEntity notifyUserEntity;
}
