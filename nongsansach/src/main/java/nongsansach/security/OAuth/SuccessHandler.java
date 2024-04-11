package nongsansach.security.OAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;
import nongsansach.Entity.Model.Root;
import nongsansach.Entity.RolesEntity;
import nongsansach.Entity.UsersEntity;
import nongsansach.dto.RootDTO;
import nongsansach.repository.RoleRepository;
import nongsansach.service.Imp.UserServiceImp;
import nongsansach.utils.JwtUltils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    JwtUltils jwtUltils;
    @Autowired
    RoleRepository roleRepository;

    @Value("${client.baseurl}")
    private String baseurl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        toPerson(authentication.getPrincipal());
        save_user_oauth2();
        RootDTO rootDto = new RootDTO();
        rootDto.setEmail(root.getEmail());
        rootDto.setName(root.getName());

        String base64Data = jwtUltils.encodeRootDtoToBase64(rootDto);

        JSONObject responseJson = new JSONObject();
        responseJson.put("base64Data", base64Data);
        response.sendRedirect(baseurl + "index_logined.html?response=" + responseJson.toJSONString());
    }

    public Root root = new Root();

    public void toPerson(Object obj) {
        DefaultOAuth2User user = ((DefaultOAuth2User) obj);
        root.setEmail(user.getAttribute("email"));

        root.setName(user.getAttribute("name"));
        System.out.print(root.getName());
    }

    public Boolean save_user_oauth2() {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(root.getEmail());
        usersEntity.setFullname(root.getName());
        RolesEntity rolesEntity = roleRepository.findByName("User");
        usersEntity.setRole(rolesEntity);
        if (userServiceImp.check_user_oauth2(usersEntity.getEmail())) {
            return false;
        }

        userServiceImp.saveUserEntity(usersEntity);
        return true;
    }

}
