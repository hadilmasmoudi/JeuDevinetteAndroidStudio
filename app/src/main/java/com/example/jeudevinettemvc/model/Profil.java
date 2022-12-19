package com.example.jeudevinettemvc.model;

public class Profil {
    private int valeurAchercher;
    private int valeurSaisie;
    private String response;
    public Profil(int valeurSaisie, int valeurAchercher)
    {
        this.valeurAchercher = valeurAchercher;
        this.valeurSaisie = valeurSaisie;
    }
    public String getResponse() {
        if (valeurSaisie==valeurAchercher)
            response = "Bravo! Vous avez trouvé la valeur";
        if (valeurSaisie<valeurAchercher)
            response = "Veuillez saisir une valeur plus grande";
        if (valeurSaisie>valeurAchercher)
            response = "Veuillez saisir une valeur plus petite";
        return response;
    }
    public int getValeurAchercher() {
        return valeurAchercher;
    }
    public int getValeurSaisie() {
        return valeurSaisie;
    }
}
