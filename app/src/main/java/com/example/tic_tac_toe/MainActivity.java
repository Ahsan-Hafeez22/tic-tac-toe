package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView playerOneScore, playerTwoScore, playerStatus;
    Button[] btn = new Button[9];
    Button reset, playAgain;
    int playerOneScoreCount, playerTwoScoreCount;
    boolean playerOneActive;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //1 for X
    //0 for 0
    //2 for empty
    int[][] winningPos = {{0,1,2}, {3,4,5}, {6,7,8},
                          {0,3,6}, {1,4,7},{2,5,8},
                          {0,4,8}, {2,4,6}};
            // 0        1       2
            // 3        4       5
            // 6        7       8
    int rounds = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_component();
        SettingUpListeners();

    }

    private void SettingUpListeners() {
        for(int i=0; i<btn.length; i++){
            btn[i].setOnClickListener(this);
        }
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                play_Again();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });
        playAgain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                play_Again();
            }

        });
    }

    @SuppressLint("SetTextI18n")
    private void play_Again() {
        rounds = 0;
        playerOneActive = true;
        for(int i=0; i< btn.length; i++){
            gameState[i] = 2;
            btn[i].setText("");
        }
        playerStatus.setText("Status");
    }
    @SuppressLint("SetTextI18n")
    private void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString(). equals(""))
        {
            return;
        }
        else if(checkWinner()){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length()));
        if (playerOneActive){
            playerStatus.setText("Player-2 Turn");
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 0;

        }
        else {
            playerStatus.setText("Player-1 Turn");
            ((Button) view).setText("0");
            ((Button) view).setTextColor(Color.parseColor("#70fc3a"));
            gameState[gameStatePointer] = 1;
        }
        rounds++;
        if (checkWinner()){
            if(playerOneActive){
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player X has Won");
            }
            else{
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player 0 has Won");
            }
        }
        else if(rounds == 9){
            playerStatus.setText("Match Draw");
        }
        else{
            playerOneActive = !playerOneActive;
        }
    }

    private boolean checkWinner() {
        boolean winnerResults = false;
        for(int[] winningPos: winningPos){
            if(gameState[winningPos[0]]==gameState[winningPos[1]] &&
                    gameState[winningPos[1]]==gameState[winningPos[2]] &&
                    gameState[winningPos[0]]!=2) {
                winnerResults = true;
            }
        }
        return winnerResults;
    }

    private void init_component() {
        playerOneScore = findViewById(R.id.score_1);
        playerTwoScore = findViewById(R.id.score_2);
        playerStatus = findViewById(R.id.status);
        playAgain = findViewById(R.id.play_again);
        reset = findViewById(R.id.reset);
        btn[0] = findViewById(R.id.btn_0);
        btn[1] = findViewById(R.id.btn_1);
        btn[2] = findViewById(R.id.btn_2);
        btn[3] = findViewById(R.id.btn_3);
        btn[4] = findViewById(R.id.btn_4);
        btn[5] = findViewById(R.id.btn_5);
        btn[6] = findViewById(R.id.btn_6);
        btn[7] = findViewById(R.id.btn_7);
        btn[8] = findViewById(R.id.btn_8);
    }



}