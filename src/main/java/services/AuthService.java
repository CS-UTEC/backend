package services;

import data.entities.Role;
import data.entities.UserApp;
import data.entities.UserWeb;
import data.repositories.UserAppRepository;
import data.repositories.UserWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "authService")
public class AuthService implements UserDetailsService {

    @Autowired
    private UserWebRepository webRepository;

    @Autowired
    private UserAppRepository appRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    
    public UserDetails loadUserByUsername(String data) throws UsernameNotFoundException {
        String role = data.substring(0, 8);
        String username = data.substring(8, data.length());

        List<GrantedAuthority> authorities = null;
        Boolean isUserWeb = role.equals("USER_WEB");
        Boolean isUserApp = role.equals("USER_APP");

        if (!isUserWeb && !isUserApp) {
            role = "USER_WEB";
            username = data;
        }
        
        if (isUserApp) {
            UserApp userApp = findByDocument(username);
            if(userApp == null){
                throw new UsernameNotFoundException("Invalid document.");
            }
            authorities = getUserAuthority(userApp.getRol());
            return buildUserForAuthApp(userApp, authorities);
        }

        UserWeb userWeb = findUserByUsername(username);
        if(userWeb == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        authorities = getUserAuthority(userWeb.getRol());
        return buildUserForAuthWeb(userWeb, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Role userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles.getName()));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    public UserWeb findUserByUsername(String username) {
        return webRepository.findByUsername(username);
    }

    public UserApp findByDocument(String document) {
        return appRepository.findByDocument(document);
    }

    private UserDetails buildUserForAuthWeb(UserWeb usuario, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

    private UserDetails buildUserForAuthApp(UserApp usuario, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(usuario.getDocument(), usuario.getDocument(), authorities);
    }

    public UserWeb save(UserWeb usuario) {
        usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return webRepository.save(usuario);
    }
}
