package pierre.zachary.tp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pierre.zachary.tp3.controller.dto.UtilisateurDTO;
import pierre.zachary.tp3.modele.FacadeUtilisateurs;
import pierre.zachary.tp3.modele.Utilisateur;
import pierre.zachary.tp3.modele.exception.LoginDejaUtiliseException;
import pierre.zachary.tp3.modele.exception.UtilisateurInexistantException;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private FacadeUtilisateurs facadeUtilisateurs;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/inscription")
    public ResponseEntity inscription(@RequestBody UtilisateurDTO u) throws LoginDejaUtiliseException {
        facadeUtilisateurs.inscrireUtilisateur(u.numEtu, passwordEncoder.encode(u.password));
        return ResponseEntity.ok(u.numEtu+" est correctement enregistrée.");
    }

    @GetMapping("/user/{numEtu}")
    public ResponseEntity<Utilisateur> getUserByNumEtu(@PathVariable String numEtu, Principal principal) throws UtilisateurInexistantException {

        // VERIF QUE LA ROUTE EST BIEN ACCEDER PAR L'UTILISATEUR AVEC LE MEME NUM ETU
        Utilisateur loggedUser = facadeUtilisateurs.getUtilisateurByLogin(principal.getName());
        if(!loggedUser.getLogin().equals(numEtu)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(facadeUtilisateurs.getUtilisateurByLogin(numEtu));
    }
}
