import java.util.*;

public class AI_main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        play_ground jogo = new play_ground();

        while(jogo.verify_game_state() == null){
            jogo.play(scan.nextInt(), scan.nextInt());
            System.out.println(jogo);
        }
        System.out.println(jogo.verify_game_state() + " wins!");

    }
}