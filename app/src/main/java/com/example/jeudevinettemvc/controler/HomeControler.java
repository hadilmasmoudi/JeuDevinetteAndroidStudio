package com.example.jeudevinettemvc.controler;
import android.content.Context;
import com.example.jeudevinettemvc.model.AccesLocal;
import com.example.jeudevinettemvc.model.Players;
import com.example.jeudevinettemvc.outils.Serilizer;
import java.util.Date;
public final class HomeControler {
    private static HomeControler instance = null;
    private static Players players;
    //Nom du fichier
    private static String nonFichier = "savePlayers";
    private static AccesLocal accesLocal;
    private HomeControler(){
        super();
    }
    public static final HomeControler getInstance(Context context)
    {
        if(HomeControler.instance == null) {
            HomeControler.instance = new HomeControler();
// recupeSerialize(context);
            accesLocal=new AccesLocal(context);
//récuper
            players=accesLocal.recupDernier();
        }
        return HomeControler.instance;
    }
    public void createPlayers (String playerName1, String playerName2,
                               Context context)
    {
        players = new Players( new Date(),playerName1,playerName2);
//Serializer.serialize(nonFichier, players, context);
        accesLocal.ajout(players);
    }
    /**
     * Récuparation de l'objet sérailiser càd players
     * @param context
     */
    private static void recupeSerialize(Context context)
    {
        players = (Players) Serilizer.deSerialize(nonFichier,context);
    }
    public String getPlayerName1()
    {
        if(players == null) return null;
        return players.getPlayerName1();
    }
    public String getPlayerName2()
    {
        if(players == null) return null;
        return players.getPlayerName2();
    }
}