package com.example.jeudevinettemvc.model;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.jeudevinettemvc.outils.MySQLiteOpenHelper;
import java.util.Date;
public class AccesLocal {
    //propriétés
    private String baseName ="bdPlayersSQLite";
    private Integer baseVersion =1 ; // version de la BD
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase db;
    /**
     * Constructeur
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, baseName, null,
                baseVersion);
    }
    /**
     * Ajout de noms de joueurs (players) dans la DB
     * @param players
     */
    public void ajout (Players players){
        db=accesBD.getWritableDatabase();
        String req="insert into players (playingDate, playerName1, playerName2) values";
        req+="(\""+ players.getPlayingDate()+ "\",\"" +
                players.getPlayerName1()+ "\",\"" + players.getPlayerName2()+"\")";
        db.execSQL(req);
    }
    /**
     * Récupération du derniers noms de joueurs (players) de la DB
     */
    public Players recupDernier(){
        db = accesBD.getReadableDatabase();
        Players players = null;
        String req = "select * from players"; // récuppérer tous les players
        Cursor curseur =db.rawQuery(req, null); // lire ligne par ligne
        curseur.moveToLast(); // se possitionner sur la dernière ligne du table players
        if (!curseur.isAfterLast()) {
// playingDate : i=0, playerName1 : i=1, playerName2 : i=2
            String playerName1 = curseur.getString(1); // playerName1 : i=1
            String playerName2 = curseur.getString(2); // playerName2 : i=2
            players=new Players(new Date(),playerName1,playerName2);
        }
        curseur.close();
        return players;
    }
}