
//package TTT_play;
import java.util.*;

public class Play_ground {
    Piece[][] play_board;
    Piece turn; // Piece to be played (Game starts with Circle)

    Play_ground() {
        play_board = new Piece[3][3];
        turn = Piece.circle;
    }

    /**
     * Clones "this" as a new object
     * 
     * @param play
     */
    Play_ground(Play_ground play) {
        play_board = new Piece[3][3];
        for (int i = 0; i < getX(); i++) {
            for (int j = 0; j < getY(); j++) {
                this.play_board[i][j] = play.play_board[i][j];
            }
        }
        this.turn = play.turn;
    }

    void setBoard(Piece[][] board) {
        this.play_board = board;
    }

    /**
     * @return Matrix column height
     */
    int getX() {
        return play_board.length;
    }

    /**
     * @return Matrix line width
     */
    int getY() {
        return play_board[0].length;
    }

    void change_turn() {
        if (this.turn == Piece.circle)
            this.turn = Piece.cross;
        else
            this.turn = Piece.circle;
    }

    void change_turn(Piece piece) {
        if (piece == Piece.circle)
            this.turn = Piece.cross;
        else
            this.turn = Piece.circle;
    }

    /**
     * Returns the winner of the game (Or "tied") or null if the game hasnt finished
     * yet
     * 
     * @return Piece
     */
    Piece verify_game_state() {
        for (int i = 0; i < play_board.length; i++) {
            if (play_board[i][0] == play_board[i][1] && play_board[i][1] == play_board[i][2])
                return play_board[i][0];
            if (play_board[0][i] == play_board[1][i] && play_board[1][i] == play_board[2][i])
                return play_board[0][i];
        }
        if (play_board[0][0] == play_board[1][1] && play_board[1][1] == play_board[2][2])
            return play_board[1][1];
        if (play_board[2][0] == play_board[1][1] && play_board[1][1] == play_board[0][2])
            return play_board[1][1];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (play_board[i][j] == null)
                    return null;

        return Piece.tied;
    }

    /**
     * Plays the turn's piece in an x,y position
     * 
     * @param x, y
     * @return "play_groud" caso seja bem sucedido ou "null" se x,y estiver ocupado
     *         ou x/y invalido
     */
    Piece[][] play(int x, int y) {
        if (x >= play_board.length)
            return null;
        if (y >= play_board[x].length)
            return null;
        if (play_board[x][y] != null)
            return null;

        play_board[x][y] = turn;
        change_turn();
        return play_board;
    }

    /**
     * Plays the piece in an x,y position
     * 
     * @param x, y, piece
     * @return "play_groud" caso seja bem sucedido ou "null" se x,y estiver ocupado
     *         ou x/y invalido
     */
    Piece[][] play(int x, int y, Piece piece) {
        if (x >= play_board.length)
            return null;
        if (y >= play_board[x].length)
            return null;
        if (play_board[x][y] != null)
            return null;

        play_board[x][y] = piece;
        change_turn(piece);
        return play_board;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < play_board.length; i++) {
            for (int j = 0; j < play_board[i].length; j++) {
                if (play_board[i][j] == Piece.circle)
                    str += "O ";
                else if (play_board[i][j] == Piece.cross)
                    str += "X ";
                else
                    str += "  ";

                if (j != play_board.length - 1)
                    str += "| ";
            }
            str += '\n';
        }
        return str;
    }

}