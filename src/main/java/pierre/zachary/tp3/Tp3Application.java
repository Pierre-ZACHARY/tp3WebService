package pierre.zachary.tp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pierre.zachary.tp3.modele.FacadeUtilisateurs;

@SpringBootApplication
public class Tp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }


    @Autowired
    FacadeUtilisateurs facadeUtilisateurs;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

            facadeUtilisateurs.inscrireUtilisateur("yohan.boichut@univ-orleans.fr",
                    passwordEncoder.encode("monMotDePasse"));

            facadeUtilisateurs.inscrireUtilisateur("gerard.menvussaa@etu.univ-orleans.fr",
                    passwordEncoder.encode("sonMotDePasse"));


        };
    }

}
