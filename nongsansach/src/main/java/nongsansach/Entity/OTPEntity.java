package nongsansach.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
@Entity
public class OTPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int name;

    private boolean value;

    @ManyToOne
    @JoinColumn(name="user_OTP")
    private UsersEntity userOTP;

    public OTPEntity(UsersEntity userOTP) {
        Random random = new Random();

        this.name = random.nextInt(900000) + 100000;
        this.userOTP = userOTP;
        this.value = false;
    }
    public OTPEntity(){}
}
