package nongsansach.Entity.Observer;

import nongsansach.Entity.NotifyEntity;
import nongsansach.Entity.Notify_UserEntity;
import nongsansach.repository.NotifyRepositiry;
import nongsansach.repository.NotifyUserRepository;
import nongsansach.service.Imp.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> observerList = new ArrayList<>() ;
    public void add(Observer observer){
        if (!observerList.contains(observer))
        {
            observerList.add(observer);
        }
    }

    public void remove(Observer observer){
        observerList.remove(observer);
    }

    public void notifyObserver(NotifyEntity notify, UserServiceImp userServiceImp, Notify_UserEntity notifyUserEntity, NotifyUserRepository notifyUserRepository, NotifyRepositiry notifyRepositiry){
        observerList.forEach(observer -> observer.notify(notify,userServiceImp, notifyUserEntity,notifyUserRepository,notifyRepositiry));
    }
    public void notifyForUser(NotifyEntity notify,UserServiceImp userServiceImp, Notify_UserEntity notifyUserEntity,NotifyUserRepository notifyUserRepository, NotifyRepositiry notifyRepositiry){
        notifyObserver(notify,userServiceImp, notifyUserEntity,notifyUserRepository,notifyRepositiry);
    }
}
