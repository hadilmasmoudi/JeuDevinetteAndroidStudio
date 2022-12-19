package com.example.jeudevinettemvc.controler;
 import com.example.jeudevinettemvc.model.Profil;
 public final class Controler {
     private static Controler instance = null;
     private static Profil profil;
     private Controler(){ super(); }
     public static final Controler getInstance() {
         if(Controler.instance == null)
             Controler.instance = new Controler();
         return Controler.instance;
     }
     public void createProfil (int valeurSaisie, int valeurAchercher)
     {
         profil = new Profil(valeurSaisie,valeurAchercher);
     }
     public String getResponse(){ return profil.getResponse(); } }

