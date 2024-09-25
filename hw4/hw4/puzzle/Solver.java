package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private  int move;
    private  List<WorldState> list;

   private class searchNode implements Comparable<searchNode> {

       public WorldState word;
       public searchNode prev;
       public int priority;
       public int moves;

       private searchNode(WorldState w, searchNode p, int m) {
           this.word = w;
           this.prev = p;
           this.moves = m;
           this.priority = moves + word.estimatedDistanceToGoal();
       }

       @Override
       public int compareTo(searchNode o) {
           return this.priority - o.priority;
       }


   }

    public   Solver(WorldState initial) {
        searchNode init = new searchNode(initial, null, 0);
        searchNode lessPriority;
        MinPQ<searchNode> pq = new MinPQ<>();
         while (!init.word.isGoal()) {
            for (WorldState w : init.word.neighbors()) {
                if (init.prev == null || !w.equals(init.prev.word)) {
                    searchNode tmp = new searchNode(w, init, init.moves + 1);
                    pq.insert(tmp);
                }
            }
            lessPriority = pq.delMin();
            init = lessPriority;
        }
         move = init.moves;
        list = new ArrayList<>();
        while (!init.word.equals(initial)) {
            list.add(0,init.word);
            init = init.prev;
        }
        list.addFirst(init.word);
    }
    public int moves() {
        return move;
    }
    public Iterable<WorldState> solution() {

        return list;
    }
}
