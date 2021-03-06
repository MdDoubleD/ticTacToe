package ly.generalassemb.drewmahrt.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView previousWinner;
    SharedPreferences sharedpreferences;
    String theWinner;
    String name1 = "";
    String name2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousWinner = (TextView) findViewById(R.id.last_winner_text);
        final TextView player1Name = (TextView) findViewById(R.id.player_one_name );
        final TextView player2Name = (TextView) findViewById(R.id.player_two_name);
        final Button startGame = (Button) findViewById(R.id.start_game_button);

            startGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    name1 = (player1Name).getText().toString();
                    name2 = (player2Name).getText().toString();
                    intent.putExtra("Player1", name1);
                    intent.putExtra("Player2", name2);
                    startActivity(intent);
                }
            });
    }

    @Override
    protected void onResume() {

        super.onResume();
        sharedpreferences = getApplicationContext().getSharedPreferences("naughtsAndCrossesKey", getApplicationContext().MODE_PRIVATE);
        theWinner = sharedpreferences.getString("winner", "");

        if (theWinner == null) {
            previousWinner.setText("");
        } else if (theWinner == "1") {
                previousWinner.setText(name1 + (getResources().getString(R.string.wasthewinner)));
            } else if (theWinner == "2"){
            previousWinner.setText(name2 + (getResources().getString(R.string.wasthewinner)));
        }
    }
}
