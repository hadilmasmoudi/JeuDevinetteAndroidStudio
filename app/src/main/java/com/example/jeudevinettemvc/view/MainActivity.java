package com.example.jeudevinettemvc.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jeudevinettemvc.R;
import com.example.jeudevinettemvc.controler.Controler;
public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private TextView txtResultat;
    private TextView txtTurnPlayer1, txtTurnPlayer2;
    private Button btnComparer;
    private Button btnRetour;
    private Controler controler;
    private String namePlayer1;
    private String namePlayer2;
    private int valeurAchercher;
    private int playerTurn = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPlayersNames();
        init();
        ecouteComparer();
        ecouteRetour();
        this.controler = Controler.getInstance();
    }
    private void getPlayersNames() {
        Intent intent = getIntent();
        namePlayer1 = intent.getStringExtra("player1");
        namePlayer2 = intent.getStringExtra("player2");
    }
    private void init()
    {
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtResultat=(TextView)findViewById(R.id.txtResultat);
        btnComparer=(Button)findViewById(R.id.btnComparer);
        btnRetour=(Button)findViewById(R.id.btnRetour);
        txtTurnPlayer1=(TextView)findViewById(R.id.txtTurnPlayer1);
        txtTurnPlayer2=(TextView)findViewById(R.id.txtTurnPlayer2);
        txtTurnPlayer1.setText(namePlayer1);

        txtTurnPlayer1.setBackgroundResource(R.drawable.player_turn_border_form);
        txtTurnPlayer2.setText(namePlayer2);
        txtNombre.setText("");
        txtResultat.setText("");
        valeurAchercher=(int)(Math.random() * 100) +1;
    }
    private void ecouteComparer(){
        btnComparer.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v) {
                int valeurSaisie=0;
                try {
                    valeurSaisie =
                            Integer.parseInt(txtNombre.getText().toString());
                } catch (Exception e) {}
                if (valeurSaisie==0)
                    Toast.makeText(getApplicationContext(), "Pas de valeu saisie",
                            Toast.LENGTH_SHORT).show();
                else
                    changePlayerTurn();
                afficheResultat(valeurSaisie);
                txtNombre.setText("");
                txtNombre.setHint(""+valeurSaisie);
            }
        });
    }
    private void changePlayerTurn()
    {
        if(playerTurn == 1) {

            txtTurnPlayer1.setBackgroundResource(R.drawable.player_turn_form);

            txtTurnPlayer2.setBackgroundResource(R.drawable.player_turn_border_form);
            playerTurn = 2;
        }
        else {

            txtTurnPlayer1.setBackgroundResource(R.drawable.player_turn_border_form);

            txtTurnPlayer2.setBackgroundResource(R.drawable.player_turn_form);
            playerTurn = 1;
        }
    }
    private void afficheResultat(int valeurSaisie)
    {
        this.controler.createProfil(valeurSaisie, valeurAchercher);
        if(playerTurn ==1)
            txtResultat.setText(namePlayer1 +", "+this.controler.getResponse());
        else
            txtResultat.setText(namePlayer2 +", "+this.controler.getResponse());
    }
    //** * Retour * */
    private void ecouteRetour()
    {
        btnRetour.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,
                        HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}