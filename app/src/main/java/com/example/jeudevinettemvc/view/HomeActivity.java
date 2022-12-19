package com.example.jeudevinettemvc.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jeudevinettemvc.R;
import com.example.jeudevinettemvc.controler.HomeControler;
public class HomeActivity extends AppCompatActivity {
    private EditText txtPlayer1;
    private EditText txtPlayer2;
    private Button btnJouer;
    private HomeControler homeControler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        init();
        ecouteJouer();
    }
    private void init()
    {
        txtPlayer1=(EditText)findViewById(R.id.txtNomPlayer1);
        txtPlayer2=(EditText)findViewById(R.id.txtNomPlayer2);
        btnJouer=(Button)findViewById(R.id.btnJouer);
        this.homeControler = HomeControler.getInstance(this);
//récupération des noms de joueurs
        recupPlayers();
    }
    private void ecouteJouer(){
        btnJouer.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v) {
                if(txtPlayer1.getText().toString().equals("") ||
                        txtPlayer2.getText().toString().equals("") )
                    Toast.makeText(getApplicationContext(), "Veuillez vérifier les nom des joueurs", Toast.LENGTH_SHORT).show();
                else
                {
                    savePlayers(txtPlayer1.getText().toString(),txtPlayer2.getText().toString());
                    activiteSuivante(txtPlayer1.getText().toString(),txtPlayer2.getText().toString());
                }
            }
        });
    }
    private void savePlayers(String name1, String name2) {
        this.homeControler.createPlayers(name1,name2,this);
    }
    private void activiteSuivante(String namePlayer1, String namePlayer2)
    {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("player1",namePlayer1);
        intent.putExtra("player2",namePlayer2);
        startActivity(intent);
    }
    /**
     * récupération des noms de joueurs s'ils étaient sérialisés
     */
    private void recupPlayers(){
        if(homeControler.getPlayerName1()!= null){
            txtPlayer1.setText(homeControler.getPlayerName1());
            txtPlayer2.setText(homeControler.getPlayerName2());
        }
    }
}