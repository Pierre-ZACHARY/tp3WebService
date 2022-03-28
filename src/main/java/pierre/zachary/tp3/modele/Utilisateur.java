package pierre.zachary.tp3.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Utilisateur {

    private boolean isProf;
    private String login;

    @JsonIgnore
    private String motDePasse;
    private final int idUtilisateur;
    private static int IDS=0;

    public Utilisateur(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
        try{
            this.isProf = login.split("@")[1].equals("univ-orleans.fr");
        }
        catch (Exception e){
            this.isProf = false;
        }
        this.idUtilisateur = IDS++;
    }

    public String getLogin() {
        return login;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }


    public boolean verifierMotDePasse(String motDePasse){
        return this.motDePasse.equals(motDePasse);
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public Boolean getIsProf() {
        return isProf;
    }

    public void setProf(Boolean admin) {
        isProf = admin;
    }
}