import java.util.*;

public class AI_main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AI_play ai_jogo = new AI_play(new Play_ground());
        ai_jogo.generate_every_kid();

        int x, y;
        while (ai_jogo.verify_game_state() == null) {
            System.out.println("Turno: " + ai_jogo.turn);

            ai_jogo = ai_jogo.play(scan.nextInt(), scan.nextInt());
            while (ai_jogo == null) {
                System.out.println("Jogada Inválida");
                ai_jogo = ai_jogo.play(scan.nextInt(), scan.nextInt());
            }

            System.out.println("Board atual:\n" + ai_jogo);

            if (ai_jogo.verify_game_state() != null)
                break;

            System.out.println("Turno: " + ai_jogo.turn);

            ai_jogo = ai_jogo.ai_play();
            System.out.println("Board atual:\n" + ai_jogo);

        }
    }
}