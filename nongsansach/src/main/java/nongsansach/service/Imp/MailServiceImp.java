package nongsansach.service.Imp;

import nongsansach.Entity.Model.MailStructure;

public interface MailServiceImp {
    public void sendMail(String mail, MailStructure mailStructure);
}
