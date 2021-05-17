import java.util.*;

class AI_play{
    play_ground father;
    int winner;
    LinkedList<AI_play> kids;

    AI_play(play_ground ai_play_ground){
        this.father = ai_play_ground;
    }

    void create_kids(){
        kids = new LinkedList<AI_play>();
        for(int i = 0; i < ai_play_ground.getX(); i++)
            for(int j = 0; j < ai_play_ground.getY(); j++){
                play_groud kid = new play_ground(father);
            }
    }
}