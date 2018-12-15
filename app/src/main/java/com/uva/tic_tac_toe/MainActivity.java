package com.uva.tic_tac_toe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Game game;
    TileState state;

    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private boolean block_buttons = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();

        // find the 9 fields based on button id
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the 9 fields + game state
        outState.putSerializable("Game", game);
        outState.putCharSequence("button5", button5.getText());
        outState.putBoolean("block_buttons", block_buttons);
        outState.putCharSequence("button6", button6.getText());
        outState.putCharSequence("button7", button7.getText());
        outState.putCharSequence("button8", button8.getText());
        outState.putCharSequence("button9", button9.getText());
        outState.putCharSequence("button10", button10.getText());
        outState.putCharSequence("button11", button11.getText());
        outState.putCharSequence("button12", button12.getText());
        outState.putCharSequence("button13", button13.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState){
        super.onRestoreInstanceState(savedState);

        // restore the 9 fields + game state
        game = (Game) savedState.getSerializable("Game");
        button5.setText(savedState.getCharSequence("button5"));
        button6.setText(savedState.getCharSequence("button6"));
        button7.setText(savedState.getCharSequence("button7"));
        button8.setText(savedState.getCharSequence("button8"));
        button9.setText(savedState.getCharSequence("button9"));
        button10.setText(savedState.getCharSequence("button10"));
        button11.setText(savedState.getCharSequence("button11"));
        button12.setText(savedState.getCharSequence("button12"));
        button13.setText(savedState.getCharSequence("button13"));

        block_buttons = savedState.getBoolean("block_buttons");

        // block buttons if game has already ended
        for (int block = 5; block < 14; block++) {
            Button btn = findViewById(getResources().getIdentifier("button" + block, "id", this.getPackageName()));
            btn.setEnabled(savedState.getBoolean("block_buttons"));
        }
    }

    public void tileClicked(View view){
        int id = view.getId();
        Button btn = findViewById(id);

        // connect the button to its specific location on the grid
        switch(view.getId()){
            case R.id.button5: state = game.choose(0,0);
                break;
            case R.id.button7: state = game.choose(0,1);
                break;
            case R.id.button6: state = game.choose(0,2);
                break;
            case R.id.button8: state = game.choose(1,0);
                break;
            case R.id.button9: state = game.choose(1,1);
                break;
            case R.id.button10: state = game.choose(1,2);
                break;
            case R.id.button11: state = game.choose(2,0);
                break;
            case R.id.button12: state = game.choose(2,1);
                break;
            case R.id.button13: state = game.choose(2,2);
                break;
        }

        // check if a "X" or "O" needs to be placed on the button
        switch(state){
            case CROSS: btn.setText("X");
                if (game.Won() == GameState.PLAYER_ONE) {
                    showWinner();
                    block_buttons = false;

                    for (int block = 5; block < 14; block++){
                        btn = findViewById(getResources().getIdentifier("button" + block, "id", this.getPackageName()));
                        btn.setEnabled(false);
                    }
                }
                break;
            case CIRCLE: btn.setText("O");
                if (game.Won() == GameState.PLAYER_TWO) {
                    showWinner();
                    block_buttons = false;
                    for (int block = 5; block < 14; block++){
                        btn = findViewById(getResources().getIdentifier("button" + block, "id", this.getPackageName()));
                        btn.setEnabled(false);
                    }
                }
                break;
            case INVALID:
                // do something different
                break;
        }
    }

    // check who wins + print out a pop-up message to with the winning player
    public void showWinner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setCancelable(true);

        builder.setTitle("Congratulations");

        if (game.Won() == GameState.PLAYER_ONE) {
            builder.setMessage("Player One \"X\" has won the Game");
        }

        if (game.Won() == GameState.PLAYER_TWO){
            builder.setMessage("Player Two \"O\" has won the Game");
        }

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }


    // reset button > clears all the buttons + states
    public void resetClicked(View view) {
        game = new Game();
        for (int reset = 5; reset < 14; reset++){
            Button btn = findViewById(getResources().getIdentifier("button" + reset, "id", this.getPackageName()));
            btn.setText("");
            btn.setEnabled(true);
            block_buttons = true;
        }
    }
}

