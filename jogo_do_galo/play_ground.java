import java.util.*;

class play_ground{
    enum Piece{
        circle,
        cross,
    }

    Piece[][] play_ground;
    Piece turn; // Qual o jogador a jogar no momento (Primeiro a jogar é o Circulo)

    play_ground(){
        play_ground = new Piece[3][3];
        turn = Piece.circle;
    }

    play_ground(play_ground play){
        this.play_ground = play.play_ground;
        this.turn = play.turn;
    }

    /**
     * @return Matrix column height
     */
    int getX(){
        return play_groud.length;
    }

    /**
     * @return Matrix line width
     */
    int getY(){
        return play_groud[0].length;
    }

    void change_turn(){
        if(this.turn == Piece.circle) this.turn = Piece.cross;
        else this.turn = Piece.circle;
    }

    void change_turn(Piece piece){
        if(piece == Piece.circle) this.turn = Piece.cross;
        else this.turn = Piece.circle;
    }

    /**
     * Retorna o vencedor do jogo ou "null" em caso de empate
     * @return Piece
     */
    Piece verify_game_state(){
        for(int i = 0; i < play_ground.length; i++){
            if(play_ground[i][0] == play_ground[i][1] && play_ground[i][1] == play_ground[i][2])
                return play_ground[i][0];
            if(play_ground[0][i] == play_ground[1][i] && play_ground[1][i] == play_ground[2][i])
                return play_ground[0][i];
        }
        if(play_ground[0][0] == play_ground[1][1] && play_ground[1][1] == play_ground[2][2])
            return play_ground[1][1];
        if(play_ground[2][0] == play_ground[1][1] && play_ground[1][1] == play_ground[0][2])
            return play_ground[1][1];

        return null;
    }

    /**
     * Marca uma posição com a peça do jogador que devia jogar num dado turno
     * @param x, y
     * @return "play_groud" caso seja bem sucedido ou "null" se x,y estiver ocupado ou x/y invalido
     */
    Piece[][] play(int x, int y){
        if(x >= play_ground.length)
            return null;
        if(y >= play_ground[x].length)
            return null;
        if(play_ground[x][y] != null)
            return null;
        
        play_ground[x][y] = turn;
        change_turn();
        return play_ground;
    }


    /**
     * Marca uma posição com a peça do jogador
     * @param x, y, piece
     * @return "play_groud" caso seja bem sucedido ou "null" se x,y estiver ocupado ou x/y invalido
     */
    Piece[][] play(int x, int y, Piece piece){
        if(x >= play_ground.length)
            return null;
        if(y >= play_ground[x].length)
            return null;
        if(play_ground[x][y] != null)
            return null;
        
        play_ground[x][y] = piece;
        change_turn(piece);
        return play_ground;
    }

    @Override
        public String toString() {
            String str = "";
            for(int i = 0; i < play_ground.length; i++){
                for(int j = 0; j < play_ground[i].length; j++){
                    if(play_ground[i][j] == Piece.circle)
                        str += "O ";
                    else if(play_ground[i][j] == Piece.cross)
                        str += "X ";
                    else str += "  ";

                    if(j != play_ground.length-1)
                        str += "| ";
                }
                str+= '\n';
            }
            return str;
        }

}