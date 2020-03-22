package services;

import data.entities.Role;
import data.entities.UserWeb;
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
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWeb user = findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> authorities = getUserAuthority(user.getRol());
        return buildUserForAuthentication(user, authorities);
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

    private UserDetails buildUserForAuthentication(UserWeb usuario, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

    public UserWeb save(UserWeb usuario) {
        usuario.setId(sequenceGeneratorService.generateSequence(UserWeb.SEQUENCE_NAME));
        usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return webRepository.save(usuario);
    }
}
