package nongsansach.service.Imp;

public interface LoginServiceImp {
    String checkLogin(String username,String password);

    String checkLoginByGmail(String email);

}
