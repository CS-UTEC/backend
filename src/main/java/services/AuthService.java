package services;

import data.entities.Role;
import data.entities.Usuario;
import data.repositories.UsuarioRepository;
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
import java.util.Optional;
import java.util.Set;


@Service(value = "authService")
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = findUserByEmail(username);
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

    public Usuario findUserByEmail(String username) {
        return repository.findUsuarioByEmail(username);
    }

    private UserDetails buildUserForAuthentication(Usuario usuario, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), authorities);
    }

    public Usuario findById(long id) {
        Optional<Usuario> optionalUser = repository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    public Usuario updatePassword(Long codigo) {
        Usuario user = findById(codigo);
        if(user != null) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            repository.save(user);
        }
        return user;
    }

    public Usuario save(Usuario usuario) {
        usuario.setId(sequenceGeneratorService.generateSequence(Usuario.SEQUENCE_NAME));
        usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return repository.save(usuario);
    }
}
