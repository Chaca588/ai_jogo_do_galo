//package TTT_play;

//import TTT_play.*;
import java.util.*;

class AI_play {
    Boolean DEBUG = false;

    class Move {
        int x, y;

        Move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            String str = this.x + ", " + this.y;
            return str;
        }

        public boolean equals(Move o) {
            // If the object is compared with itself then return true
            if (o.x == this.x && o.y == this.y) {
                return true;
            }
            return false;
        }
    }

    Play_ground father;
    Piece winner;
    LinkedList<AI_play> kids;
    Move movement;
    Piece turn;
    AI_play best_kid;

    AI_play(Play_ground ai_play_ground) {
        this.father = new Play_ground(ai_play_ground);
        this.turn = this.father.turn;
    }

    /**
     * Returns the Child node relative to a certaint move
     * 
     * @param Move
     * @return Child node
     */
    AI_play getChild(Move m) {
        AI_play child = null;
        if (DEBUG)
            System.out.println("===========");
        for (AI_play kid : kids) {
            if (DEBUG) {
                System.out.println(kid.winner);
                System.out.println(kid);
            }
            if (kid.movement.equals(m))
                child = kid;
        }
        if (DEBUG)
            System.out.println("===========");

        return child;
    }

    /**
     * Returns the Child node relative to a certaint move
     * 
     * @param x
     * @param y
     * @return Child node
     */
    AI_play getChild(int x, int y) {
        Move m = new Move(x, y);
        AI_play child = null;
        if (DEBUG)
            System.out.println("===========");
        for (AI_play kid : kids) {
            if (DEBUG) {
                System.out.println(kid.winner);
                System.out.println(kid);
            }
            if (kid.movement.equals(m))
                child = kid;
        }
        if (DEBUG)
            System.out.println("===========");

        return child;
    }

    /**
     * Returns the best child node. Aka the cild that leads the game to the best
     * possible scenario for that turn
     * 
     * @return kid
     */
    AI_play getBestChild() {
        AI_play bestChild = null;
        for (AI_play kid : kids) {
            if (bestChild == null && kid.winner == Piece.tied)
                bestChild = kid;
            if (kid.winner == turn) {
                bestChild = kid;
            }
        }
        if (bestChild != null)
            return bestChild;

        if (this.kids.size() > 0)
            return kids.get(0);
        return null;
    }

    /**
     * Generates a child node for every possible play and links them to a list of
     * childs
     */
    void create_kids() {
        if (this.kids != null)
            return;

        AI_play child;
        this.kids = new LinkedList<AI_play>();
        for (int i = 0; i < father.getX(); i++)
            for (int j = 0; j < father.getY(); j++) {
                Play_ground kid = new Play_ground(father);

                if (kid.play(i, j) != null) {
                    child = new AI_play(kid);
                    child.turn = kid.turn;
                    child.movement = new Move(i, j);

                    if (child.father.verify_game_state() != null)
                        child.winner = child.father.verify_game_state();

                    kids.add(child);
                }
            }
    }

    /**
     * Generates every child node (Every possible play from the main board)
     */
    void generate_every_kid() {
        this.create_kids();
        for (AI_play kid : kids) {
            if (kid.father.verify_game_state() == null) {
                kid.generate_every_kid();
            }
        }
        this.best_kid = getBestChild();
        this.winner = this.best_kid.winner;
    }

    /**
     * Print every child board
     */
    void print_every_child() {
        for (AI_play play : this.kids) {
            System.out.println(play.movement);
            System.out.println(play.father);
        }

    }

    /**
     * Prints the board of the Child that leads the game to the best scenario
     * possible
     */
    void print_bestChild() {
        if (best_kid != null)
            System.out.println("Best Child (" + best_kid.movement.x + ", " + best_kid.movement.y + "): \n" + best_kid);
    }

    /**
     * Returns the Child node relative to a certaint move
     * 
     * @param x
     * @param y
     * @return Child Node
     */
    AI_play play(int x, int y) {
        return getChild(x, y);
    }

    /**
     * Let the AI play the best play he knows
     * 
     * @return The board with its changes
     */
    AI_play ai_play() {
        return play(best_kid.movement.x, best_kid.movement.y);
    }

    /**
     * Verify whether the game ends or not
     * 
     * @return Winner (or Tied if there is none)
     */
    Piece verify_game_state() {
        Piece winner = father.verify_game_state();
        if (winner == Piece.tied)
            System.out.println("Tied!");
        else if (winner != null)
            System.out.println(winner + " wins!");
        return winner;
    }

    @Override
    public String toString() {
        return this.father.toString();
    }
}