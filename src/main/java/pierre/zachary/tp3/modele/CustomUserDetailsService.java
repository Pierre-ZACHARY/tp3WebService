package pierre.zachary.tp3.modele;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pierre.zachary.tp3.modele.exception.UtilisateurInexistantException;


public class CustomUserDetailsService implements UserDetailsService {
    private static final String[] ROLES_ADMIN = {"ENSEIGNANT"};
    private static final String[] ROLES_USER = {"ETUDIANT", "ENSEIGNANT"};

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FacadeUtilisateurs facadeUtilisateurs;

    @Override
    public UserDetails loadUserByUsername(String s){
        Utilisateur utilisateur = null;
        try {

            utilisateur = facadeUtilisateurs.getUtilisateurByLogin(s);
        } catch (UtilisateurInexistantException e) {
            throw  new UsernameNotFoundException("User "+s+" not found");
        }
        if (utilisateur==null) {
            throw  new UsernameNotFoundException("User "+s+" not found");
        }
        System.out.println(utilisateur);
        String[] roles = utilisateur.getIsProf() ? ROLES_ADMIN : ROLES_USER;
        UserDetails userDetails = User.builder()
                .username(utilisateur.getLogin())
                .password(utilisateur.getMotDePasse())
                .roles(roles)
                .build();

        return userDetails;
    }
}