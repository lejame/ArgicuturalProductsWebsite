package nongsansach.security;


import com.cloudinary.Cloudinary;
import nongsansach.config.FacebookSignInAdapter;
import nongsansach.filter.CustomJwtFilter;
import nongsansach.security.OAuth.CustomSuccessHandler;
import nongsansach.security.OAuth.SuccessHandler;

import nongsansach.service.FacebookConnectionSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    SuccessHandler successHandler;
    @Autowired
    CustomJwtFilter customJwtFilter;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(c -> c.disable())
                .authorizeHttpRequests(a -> a
                        .anyRequest().permitAll()
                        .and()
                        .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class))
                .oauth2Login(o2 -> o2
                        .successHandler(successHandler));

        return http.build();
    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ConnectionFactoryLocator connectionFactoryLocator =
                connectionFactoryLocator();
        UsersConnectionRepository usersConnectionRepository =
                getUsersConnectionRepository(connectionFactoryLocator);
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository, new FacebookSignInAdapter());
    }

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "du0alko2s");
        config.put("api_key", "966956591298138");
        config.put("api_secret", "kKBLDYE2iH8eQvklNa_HtsjojDg");
        config.put("secure", true);
        return new Cloudinary(config);
    }


    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    String appSecret;

    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    String appId;
    private ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));
        return registry;
    }

    private UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator
                                                                           connectionFactoryLocator) {
        return new InMemoryUsersConnectionRepository(connectionFactoryLocator);
    }
}
