package com.uva.tic_tac_toe;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;


    // create game board initialized to "BLANK"
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    // Choose the new state of the button > CROSS or CIRCLE
    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK) {
            if (playerOneTurn) {
                playerOneTurn = false;
                board[row][column] = TileState.CROSS;
                return TileState.CROSS;
            }

            else {
                playerOneTurn = true;
                board[row][column] = TileState.CIRCLE;
                return TileState.CIRCLE;
            }
        }

        else {
            return TileState.INVALID;
        }
    }

    // check who wins the game
    public GameState Won(){
        // player one wins > X
        for (int x = 0; x < 3; x++){
            if (board[x][0] == TileState.CROSS && board[x][1] == TileState.CROSS && board[x][2] == TileState.CROSS){
                return GameState.PLAYER_ONE;
            }
        }

        for (int y = 0; y < 3; y++){
            if (board[0][y] == TileState.CROSS && board[1][y] == TileState.CROSS && board[2][y] == TileState.CROSS){
                return GameState.PLAYER_ONE;
            }
        }

        if (board[0][0] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[2][0] == TileState.CROSS && board[1][1] == TileState.CROSS && board[0][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }

        // player two wins > O
        for (int x = 0; x < 3; x++){
            if (board[x][0] == TileState.CIRCLE && board[x][1] == TileState.CIRCLE && board[x][2] == TileState.CIRCLE){
                return GameState.PLAYER_TWO;
            }
        }

        for (int y = 0; y < 3; y++){
            if (board[0][y] == TileState.CIRCLE && board[1][y] == TileState.CIRCLE && board[2][y] == TileState.CIRCLE){
                return GameState.PLAYER_TWO;
            }
        }

        if (board[0][0] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[2][0] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[0][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }

        else {
            return GameState.IN_PROGRESS;
        }
    }
}
