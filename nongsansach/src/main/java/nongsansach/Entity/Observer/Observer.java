package nongsansach.Entity.Observer;

import nongsansach.Entity.NotifyEntity;
import nongsansach.Entity.Notify_UserEntity;
import nongsansach.repository.NotifyRepositiry;
import nongsansach.repository.NotifyUserRepository;
import nongsansach.service.Imp.UserServiceImp;

public interface Observer {
    void notify(NotifyEntity a, UserServiceImp userServiceImp, Notify_UserEntity notifyUserEntity, NotifyUserRepository notifyUserRepository, NotifyRepositiry notifyRepositiry);
}
