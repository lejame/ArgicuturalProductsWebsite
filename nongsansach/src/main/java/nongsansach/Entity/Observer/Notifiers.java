package nongsansach.Entity.Observer;

import nongsansach.Entity.NotifyEntity;
import nongsansach.Entity.Notify_UserEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.repository.NotifyRepositiry;
import nongsansach.repository.NotifyUserRepository;
import nongsansach.service.Imp.UserServiceImp;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class Notifiers implements Observer{


    @Override
    public void notify(NotifyEntity a, UserServiceImp userServiceImp, Notify_UserEntity notifyUserEntity, NotifyUserRepository notifyUserRepository, NotifyRepositiry notifyRepositiry) {
        List<UsersEntity> usersEntities = userServiceImp.getAll();
        notifyUserEntity.getNotifyEntities().add(a);

        for (UsersEntity user: usersEntities) {
            notifyUserEntity.getUsersEntities().add(user);
            user.setNotifyUserEntity(notifyUserEntity);
        }
        Notify_UserEntity notifyUserEntity1 = notifyUserRepository.save(notifyUserEntity);
        a.setNotifyUserEntity(notifyUserEntity1);
        notifyRepositiry.save(a);
    }
}
