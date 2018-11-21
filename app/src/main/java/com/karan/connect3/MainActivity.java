package com.karan.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=1;
    // gameActive will become false when someone wins to constrain anyone to click on ImageView
    boolean gameActive = true;
    // 3 means ImageView is not tapped yet when it is tapped it is replaced by activePlayer
    int[] state={3,3,3,3,3,3,3,3,3};
    int[][] winningTags= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void bring(View view) {

        ImageView pointer = (ImageView) view;
        int tapped = Integer.parseInt(pointer.getTag().toString());
        if (state[tapped] == 3 && gameActive) {
            state[tapped] = activePlayer;
            pointer.setTranslationY(-1000f);
            if (activePlayer == 1) {
                pointer.setImageResource(R.drawable.cross1);
                activePlayer = 2;

            } else {
                pointer.setImageResource(R.drawable.circle2);
                activePlayer = 1;
            }
            pointer.animate().translationYBy(1000f).rotation(360f).setDuration(1);
            for (int[] win : winningTags) {
                if (state[win[0]] == state[win[1]] && state[win[1]] == state[win[2]] && state[win[0]] != 3) {
                    gameActive = false;
                    String winner = "Player 1 has WON!!";
                    if (state[win[0]] == 2)
                        winner = "Player 2 has WON!!";
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.winnerMessageLinearLayout);
                    layout.setVisibility(View.VISIBLE);
                    LinearLayout layout1 = (LinearLayout) findViewById(R.id.playAgianLinearLayout);
                    layout1.setVisibility(View.VISIBLE);
                } else {
                    boolean gameOver = true;
                    for (int draw : state) {
                        if (draw == 3)
                            gameOver = false;
                    }
                    if (gameOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw!!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.winnerMessageLinearLayout);
                        layout.setVisibility(View.VISIBLE);
                        LinearLayout layout1 = (LinearLayout) findViewById(R.id.playAgianLinearLayout);
                        layout1.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
        public void playAgain(View view){
        gameActive = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.winnerMessageLinearLayout);
            layout.setVisibility(View.INVISIBLE);
            LinearLayout layout1 = (LinearLayout) findViewById(R.id.playAgianLinearLayout);
            layout1.setVisibility(View.INVISIBLE);
        activePlayer = 1;
        for (int i = 0; i < state.length; i++) {
            state[i] = 3;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
