package nongsansach.Entity.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
public class Root{
    public String at_hash;
    public String sub;
    public boolean email_verified;
    public String iss;
    public String given_name;
    public String nonce;
    public String picture;
    public ArrayList<String> aud;
    public String azp;
    public String name;
    public Date exp;
    public String family_name;
    public Date iat;
    public String email;
}